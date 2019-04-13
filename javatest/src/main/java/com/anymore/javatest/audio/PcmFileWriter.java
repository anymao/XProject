package com.anymore.javatest.audio;

import android.support.annotation.NonNull;
import timber.log.Timber;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * pcm文件写入
 * Created by anymore on 2019/4/9.
 */
public class PcmFileWriter {

    private static final String PCM_FILE_SUFFIX = ".pcm";
    private static final String WAV_FILE_SUFFIX = ".wav";

    private String pcmDir;
    private String wavDir;

    private String pcmFileName;
    private String wavFileName;

    private File mPcmFile;
    private FileOutputStream mFileOutputStream;
    private BufferedOutputStream mOutputStream;
    private final boolean writeToFile;//init only once

    public PcmFileWriter(String baseDir, String fileName) {
        this(baseDir,fileName,true);
    }

    public PcmFileWriter(String baseDir, String fileName,boolean writeToFile) {
        pcmDir = baseDir+ File.separator+"pcm"+File.separator;
        wavDir = baseDir+File.separator+"wav"+File.separator;
        this.pcmFileName = pcmDir+fileName+PCM_FILE_SUFFIX;
        this.wavFileName = wavDir+fileName+WAV_FILE_SUFFIX;
        this.writeToFile = writeToFile;
    }

    public void start(){
        if (writeToFile){
            try {
                File pcmDirFile = new File(pcmDir);
                if (!pcmDirFile.exists()){
                    pcmDirFile.mkdirs();
                }
                mPcmFile = new File(pcmFileName);
                if (mPcmFile.exists()){
                    mPcmFile.delete();
                }
                mPcmFile.createNewFile();
                mFileOutputStream = new FileOutputStream(mPcmFile);
                mOutputStream = new BufferedOutputStream(mFileOutputStream);
            }catch (IOException e){
                Timber.e(e);
            }
        }
    }

    public void write(@NonNull byte[] data){
        if (writeToFile){
            if (mOutputStream != null){
                try {
                    mOutputStream.write(data,0,data.length);
                    mOutputStream.flush();
                } catch (IOException e) {
                    Timber.e(e);
                }
            }
        }
    }

    public void finish(){
        Timber.d("finish");
        if (writeToFile){
            try {
                if (mOutputStream != null){
                    mOutputStream.close();
                }
            }catch (IOException e){
                Timber.e(e);
            }
            try {
                if (mFileOutputStream != null){
                    mFileOutputStream.close();
                }
            }catch (IOException e){
                Timber.e(e);
            }
        }
    }
}
