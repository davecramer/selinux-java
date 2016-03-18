package com.postgresintl.selinux;

import com.sun.jna.LastErrorException;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;

/**
 * Created by davec on 2016-03-17.
 */
public interface SELinux extends Library {
  SELinux instance = (SELinux) Native.loadLibrary("selinux",SELinux.class);

  int fgetfilecon(int fd, PointerByReference con) throws LastErrorException;
  int fgetfilecon_raw(int fd, PointerByReference con) throws LastErrorException;
  int getpeercon_raw(int fd, PointerByReference con) throws LastErrorException;


}
