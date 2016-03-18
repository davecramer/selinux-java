package com.postgresintl.selinux;

import com.postgresintl.selinux.SELinux;
import com.sun.jna.LastErrorException;
import com.sun.jna.ptr.PointerByReference;
import sun.misc.SharedSecrets;

import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

      PointerByReference pointerByReference=new PointerByReference();
      try
      {
        Socket socket=null;

        FileInputStream fis = new FileInputStream("/tmp/test");

        int fd = getFileDescriptor(fis);  //getFileDescriptor(socket.getInputStream());

        int peercon = SELinux.instance.fgetfilecon_raw(fd,pointerByReference);
        System.out.println( "Peercon returned " +peercon);
        System.out.println(pointerByReference.getValue().getString(0));
      }
      catch(LastErrorException lee)
      {
        System.err.println("Error code is "+lee.getErrorCode());
        System.err.println(lee.getMessage());
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }

    }

    public static int getFileDescriptor(FileDescriptor fd) throws IOException
    {
      return SharedSecrets.getJavaIOFileDescriptorAccess().get(fd);
    }

    public static int getFileDescriptor(InputStream is) throws IOException
    {
      return getFileDescriptor( ((FileInputStream)is).getFD());
    }
    public static int getFileDescriptor(OutputStream is) throws IOException
    {
      return getFileDescriptor( ((FileOutputStream)is).getFD());
    }

}
