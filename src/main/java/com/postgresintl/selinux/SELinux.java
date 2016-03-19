package com.postgresintl.selinux;

import com.sun.jna.LastErrorException;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.PointerByReference;

/**
 * Created by davec on 2016-03-17.
 */
public interface SELinux extends Library {
  SELinux instance = (SELinux) Native.loadLibrary("selinux",SELinux.class) ;

  /**
   *
   * @return 1 if we are running on a SELinux kernel, or 0 if not or -1 if we get an error
   */
   int is_selinux_enabled() throws LastErrorException;

  /**
   *
   * @return 1 if we are running on a SELinux MLS kernel, or 0 otherwise.
   */
   int is_selinux_mls_enabled() throws LastErrorException;

  /**
   * Free the memory allocated for a context by any of the below get* calls.
   * @param nativePeer
   */
   void freecon( int nativePeer) throws LastErrorException;

/* Free the memory allocated for a context array by security_compute_user. */
   void freeconary(PointerByReference context) throws LastErrorException;


/* Wrappers for the /proc/pid/attr API. */

  /**
   * Get current context, and set *con to refer to it.
   * Caller must free via freecon
   * @param context
   * @return
   */
   int getcon(PointerByReference context) throws LastErrorException;
  /**
   * Get current context, and set *con to refer to it.
   * Caller must free via freecon
   * @param context
   * @return
   */
  int getcon_raw(PointerByReference context) throws LastErrorException;

  /**
   *
   * Set the current security context to con.
   * Note that use of this function requires that the entire application
   * be trusted to maintain any desired separation between the old and new
   * security contexts, unlike exec-based transitions performed via setexeccon.
   * When possible, decompose your application and use setexeccon()+execve()
   * instead. Note that the application may lose access to its open descriptors
   * as a result of a setcon() unless policy allows it to use descriptors opened
   * by the old context.
   * @param context
   * @return
   */
   int setcon(final String context) throws LastErrorException;

  /**
   *
   * Set the current security context to con.
   * Note that use of this function requires that the entire application
   * be trusted to maintain any desired separation between the old and new
   * security contexts, unlike exec-based transitions performed via setexeccon.
   * When possible, decompose your application and use setexeccon()+execve()
   * instead. Note that the application may lose access to its open descriptors
   * as a result of a setcon() unless policy allows it to use descriptors opened
   * by the old context.
   * @param context
   * @return
   */
   int setcon_raw(final String context) throws LastErrorException;

  /**
   *
   * Get context of process identified by pid, and
   * set *con to refer to it.  Caller must free via freecon.
   *
   * @param pid
   * @param context
   * @return
   */
   int getpidcon(int pid, PointerByReference context) throws LastErrorException;

  /**
   *
   * Get context of process identified by pid, and
   * set *con to refer to it.  Caller must free via freecon.
   *
   * @param pid
   * @param context
   * @return
   */
   int getpidcon_raw(int pid, PointerByReference context) throws LastErrorException;


  /**
   * Get previous context (prior to last exec), and set *con to refer to it.
   * Caller must free via freecon.
   * @param context
   * @return
   */
   int getprevcon(PointerByReference context) throws LastErrorException;

  /**
   * Get previous context (prior to last exec), and set *con to refer to it.
   * Caller must free via freecon.
   * @param context
   * @return
   */
  int getprevcon_raw(PointerByReference context) throws LastErrorException;

  /**
   * Get exec context, and set *con to refer to it.
   * Sets *con to NULL if no exec context has been set, i.e. using default.
   * If non-NULL, caller must free via freecon.
   * @param context
   * @return
   */
   int getexeccon(PointerByReference context) throws LastErrorException;

  /**
   * Get exec context, and set *con to refer to it.
   * Sets *con to NULL if no exec context has been set, i.e. using default.
   * If non-NULL, caller must free via freecon.
   * @param context
   * @return
   */
  int getexeccon_raw(PointerByReference context) throws LastErrorException;

  /**
   * Set exec security context for the next execve.
   Call with NULL if you want to reset to the default.
   * @param context
   * @return
   */
   int setexeccon(final String context) throws LastErrorException;

  /**
   * Set exec security context for the next execve.
   Call with NULL if you want to reset to the default.
   * @param context
   * @return
   */
   int setexeccon_raw(final String context) throws LastErrorException;

  /**
   * Get fscreate context, and set *con to refer to it.
   * Sets *con to NULL if no fs create context has been set, i.e. using default.
   *If non-NULL, caller must free via freecon.
   * @param context
   * @return
   */
   int getfscreatecon(PointerByReference context) throws LastErrorException;

  /**
   * Get fscreate context, and set *con to refer to it.
   * Sets *con to NULL if no fs create context has been set, i.e. using default.
   *If non-NULL, caller must free via freecon.
   * @param context
   * @return
   */
   int getfscreatecon_raw(PointerByReference context) throws LastErrorException;

/* Set the fscreate security context for subsequent file creations.
   Call with NULL if you want to reset to the default. */

  /**
   * 
   * @param contexttext
   * @return
   */
   int setfscreatecon(final String contexttext) throws LastErrorException;
   int setfscreatecon_raw(final String contexttext) throws LastErrorException;


  /**
   * Get keycreate context, and set context to refer to it.
   * Sets context to NULL if no key create context has been set, i.e. using default.
   * use Pointer pointer = pointerByReference.getValue() then pointer.getValue to get
   * the context string
   * If non-NULL, caller must free via freecon, use
   * Pointer.nativeValue(pointer) to get the argument to free
   * @param context
   * @return
   */
   int getkeycreatecon(PointerByReference context) throws LastErrorException;
   int getkeycreatecon_raw(PointerByReference context) throws LastErrorException;

  /**
   * Set the keycreate security context for subsequent key creations.
   * Call with NULL if you want to reset to the default.
   * @param contexttext
   * @return
   */
   int setkeycreatecon(final String contexttext) throws LastErrorException;
   int setkeycreatecon_raw(final String contexttext) throws LastErrorException;

  /**
   *  Get sockcreate context, and set *con to refer to it.
   *  Sets *con to NULL if no socket create context has been set, i.e. using default.
   *  If non-NULL, caller must free via freecon.
   * @param context
   * @return
   **/
   int getsockcreatecon(PointerByReference context) throws LastErrorException;
   int getsockcreatecon_raw(PointerByReference context) throws LastErrorException;

  /**
   * Set the sockcreate security context for subsequent socket creations.
   * Call with NULL if you want to reset to the default.
   * @param contexttext
   * @return
   */
   int setsockcreatecon(final String contexttext) throws LastErrorException;
   int setsockcreatecon_raw(final String contexttext) throws LastErrorException;

/* Wrappers for the xattr API. */

  /**
   * Get file context, and set *con to refer to it.
   * Caller must free via freecon. 
   * @param path
   * @param context
   * @return
   */
   int getfilecon(final String path, PointerByReference context) throws LastErrorException;
   int getfilecon_raw(final String path, PointerByReference context) throws LastErrorException;
   int lgetfilecon(final String path, PointerByReference context) throws LastErrorException;
   int lgetfilecon_raw(final String path, PointerByReference context) throws LastErrorException;
  int fgetfilecon(int fd, PointerByReference context) throws LastErrorException;
  int fgetfilecon_raw(int fd, PointerByReference context) throws LastErrorException;

  /**
   * Set file context  
   * @param path
   * @param context
   * @return
   */
   int setfilecon(final String path, final String context) throws LastErrorException;
   int setfilecon_raw(final String path, final String context) throws LastErrorException;
   int lsetfilecon(final String path, final String context) throws LastErrorException;
   int lsetfilecon_raw(final String path, final String context) throws LastErrorException;
   int fsetfilecon(int fd, final String context) throws LastErrorException;
   int fsetfilecon_raw(int fd, final String context) throws LastErrorException;

/* Wrappers for the socket API */

  /**
   * Get context of peer socket, and set *con to refer to it.
   * Caller must free via freecon. 
   * @param fd
   * @param context
   * @return
   */
   int getpeercon(int fd, PointerByReference context) throws LastErrorException;
   int getpeercon_raw(int fd, PointerByReference context) throws LastErrorException;




}
