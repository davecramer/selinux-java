package com.postgresintl.selinux;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

/**
 * Created by davec on 2016-03-18.
 */
public class TestMain {
  public static void main(String []args)
  {
    PointerByReference pointerByReference = new PointerByReference();
    int peercon = TestLib.instance.testfunc(1,pointerByReference);
    Pointer pointer = pointerByReference.getValue();
    System.out.println(pointerByReference.getValue().getString(0));
    Native.free( Pointer.nativeValue(pointer) );
  }
}
