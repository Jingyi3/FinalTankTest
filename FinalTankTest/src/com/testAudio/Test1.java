//package com.testAudio;
////
////import java.io.*;
////import java.util.*;
////
////import javax.sound.*;
////import javax.sound.sampled.*;
////import javax.swing.*;
////import java.awt.*;
/////**
//// * Created by ZJYYY on 2018/1/14.
//// */
////public class Test1 {
////    //创建一个AePlayerWave的对象实例
////    //AePlayerWave aePlayerWave = new AePlayerWave("/111.wav");
////    //启动该线程并播放
////    AePlayerWave apw = new AePlayerWave("/Users/ZJYYY/Desktop/cs-resource/javaimages/111.wav");
////    apw
////
////}
////
////class AePlayerWave extends Thread {
////    private String filename;
////    public AePlayerWave(String wavefile) {
////        filename = wavefile;
////    }
////    public void run() {
////        File soundFile =new File(filename);
////
////        AudioInputStream audioInputStream = null;
////        try {
////            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        AudioFormat format = audioInputStream.getFormat();
////        SourceDataLine auline =null;
////        DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);
////
////        try {
////            auline = (SourceDataLine)AudioSystem.getLine(info);
////            auline.open(format);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        auline.start();
////        int nBytesRead = 0;
////        //这是缓冲
////        byte[] abData = new byte[1024];
////
////        try {
////            while(nBytesRead != -1) {
////                nBytesRead = audioInputStream.read(abData,0,nBytesRead);
////                if(nBytesRead >= 0) {
////                    auline.write(abData,0,nBytesRead);
////                }
////            }
////        }catch (Exception e) {
////            e.printStackTrace();
////        }finally {
////            auline.drain();
////            auline.close();
////        }
////
////
////
////    }
////}
//import sun.audio.*;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.io.IOException;
//
//
//import static java.lang.System.in;
//
//public class Test1{
//    //String mFlie="/Users/ZJYYY/Desktop/cs-resource/javaimages/111.wav";
//
//    try{
//
//            InputStream in= new FileInputStream("/Users/ZJYYY/Desktop/cs-resource/javaimages/111.wav");
//        AudioStream as= new AudioStream(in);
//        AudioPlayer.player.start(as);
//    }catch(Exception e) {
//        e.printStackTrace();
//    }
//
//}