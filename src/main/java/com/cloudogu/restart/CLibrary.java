package com.cloudogu.restart;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.StringArray;

interface CLibrary extends Library {

  CLibrary LIBC = Native.load("c", CLibrary.class);

  int F_GETFD = 1;
  int F_SETFD = 2;
  int FD_CLOEXEC = 1;

  int getdtablesize();

  int fcntl(int fd, int command);

  int fcntl(int fd, int command, int flags);

  int execvp(String file, StringArray args);

  String strerror(int errno);

}
