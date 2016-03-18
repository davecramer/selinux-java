package com.postgresintl.selinux;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;

/**
 * Created by davec on 2016-03-18.
 */
public interface TestLib extends Library {
  TestLib instance = (TestLib)Native.loadLibrary("test",TestLib.class);
  int testfunc(int i, PointerByReference msg);
}
