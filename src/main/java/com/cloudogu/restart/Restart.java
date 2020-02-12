package com.cloudogu.restart;

import com.sun.akuma.JavaVMArguments;
import com.sun.jna.Native;
import com.sun.jna.StringArray;

import java.io.IOException;

import static com.cloudogu.restart.CLibrary.*;

final class Restart {

  private Restart() {
  }

  static void restart() throws IOException {
    JavaVMArguments args = JavaVMArguments.current();
    args.setSystemProperty("restarted", "true");

    int sz = LIBC.getdtablesize();
    for (int i = 3; i < sz; i++) {
      int flags = LIBC.fcntl(i, F_GETFD);
      if (flags < 0) continue;
      LIBC.fcntl(i, F_SETFD, flags | FD_CLOEXEC);
    }

    // exec to self
    String exe = args.get(0);
    LIBC.execvp(exe, new StringArray(args.toArray(new String[0])));
    throw new IOException("Failed to exec '" + exe + "' " + LIBC.strerror(Native.getLastError()));
  }

}
