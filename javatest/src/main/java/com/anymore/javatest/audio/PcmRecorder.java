package com.anymore.javatest.audio;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.support.annotation.MainThread;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * pcm格式的音频录制器，为讯飞语音录制
 * Created by anymore on 2019/4/9.
 */
public class PcmRecorder {

    private int mAudioSource;//音频来源
    private int mSimpleRateInHz;//采样率
    private int mChannelConfig;
    private int mAudioFormat;
    private int mBufferSize;
    private Status mStatus;
    private AtomicBoolean mFinish;
    private Disposable mDisposable;

    private String path;
    private PcmFileWriter fileWriter;
    private OnAudioRecordingListener mListener;
    private boolean writeToFile;

    private PcmRecorder(){
        mAudioSource = MediaRecorder.AudioSource.MIC;//音频来源->mic
        mSimpleRateInHz = 16000;//采样率44.1kHz
        mChannelConfig = 2;
        mAudioFormat = AudioFormat.ENCODING_PCM_16BIT;
        mBufferSize = AudioRecord.getMinBufferSize(mSimpleRateInHz,mChannelConfig,mAudioFormat);
        writeToFile = true;
    }

    public void writeToFile(boolean enable){
        writeToFile = enable;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private static class Holder{
        private static PcmRecorder INSTANCE  = new PcmRecorder();
    }

    public static PcmRecorder getInstance(){
        return Holder.INSTANCE;
    }

    public void setOnAudioRecordingListener(OnAudioRecordingListener mListener) {
        this.mListener = mListener;
    }

    public void start(){
        if (mStatus == Status.STARTED){
            return;
        }
        mFinish = new AtomicBoolean(false);
        mDisposable = mRecordOnBackground
                .doOnSubscribe(subscription -> {
                    fileWriter = new PcmFileWriter(path, UUID.randomUUID().toString(),writeToFile);
                    fileWriter.start();
                })
                .doOnNext(bytes -> fileWriter.write(bytes))
//                .buffer(16)
//                .map(bytes -> {
//                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                    for (byte[] item:bytes){
//                        bos.write(item,0,item.length);
//                        bos.flush();
//                    }
//                    byte[] result = bos.toByteArray();
//                    bos.close();
//                    return result;
//                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bytes -> {
                    if (mListener != null){
//                        Timber.d(" mListener.onReceiveData(bytes,0,"+bytes.length+");");
                        mListener.onReceiveData(bytes,0,bytes.length);
                    }
                }, throwable -> {
                    Timber.e(throwable.getMessage());
                    reset();
                }, () -> {
                    Timber.d("结束录音");
                    reset();
                });
    }

    public void stop(){
        if (mFinish != null ){
            mFinish.compareAndSet(false,true);
        }
    }

    private void reset(){
        if (fileWriter != null){
            fileWriter.finish();
            fileWriter = null;
        }
        if (mDisposable != null && !mDisposable.isDisposed()){
            mDisposable.dispose();
        }
        mFinish = null;
        mStatus = Status.IDLE;
    }

    private final Flowable<byte[]> mRecordOnBackground = Flowable.create((FlowableOnSubscribe<byte[]>) emitter -> {
        AudioRecord mAudioRecord = new AudioRecord(mAudioSource, mSimpleRateInHz, mChannelConfig, mAudioFormat, mBufferSize);
        if (mAudioRecord.getState() == AudioRecord.STATE_INITIALIZED){
            mAudioRecord.startRecording();
            mStatus = Status.STARTED;
            mFinish.compareAndSet(true,false);
            byte[] data = new byte[mBufferSize];
            while (!mFinish.get()){
                int result = mAudioRecord.read(data,0,mBufferSize);
                if (result >= 0){
                    emitter.onNext(data);
                }else {
                    emitter.onError(new Throwable("error when read data from AudioRecord errorCode is"+result));
                    mFinish.compareAndSet(false,true);
                }
            }
            mStatus = Status.STOP;
            mAudioRecord.stop();
            mAudioRecord.release();
            emitter.onComplete();
        }else {
            emitter.onError(new Throwable("AudioRecord has not INITIALIZED"));
        }
    }, BackpressureStrategy.DROP)
            .subscribeOn(Schedulers.io());


    public enum Status{
        IDLE,//空闲(只有空闲状态才能开始)
        STARTED,//已开始
        STOP//停止(停止状态是一个暂态)之后会转换为空闲
    }

    public interface OnAudioRecordingListener{
        @MainThread
        void onReceiveData(byte[] data, int offset, int length);
    }

    public interface OnErrorListener{
        void onError(String message);
    }
}
