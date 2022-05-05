package org.nlogo.extensions.sound;

import java.io.File;
import org.nlogo.core.SyntaxJ;

/**
 * NetLogo command loops a sound file
 */
public class LoopSound
    implements org.nlogo.api.Command {
  public String getAgentClassString() {
    return "OTP";
  }

  public org.nlogo.core.Syntax getSyntax() {
    int[] right = {
      org.nlogo.core.Syntax.StringType(),
    };
    return org.nlogo.core.SyntaxJ.commandSyntax(right);
  }

  public boolean getSwitchesBoolean() {
    return false;
  }

  public org.nlogo.api.Command newInstance(String name) {
    return new LoopSound();
  }


  public void perform(org.nlogo.api.Argument args[], org.nlogo.api.Context context)
      throws org.nlogo.api.ExtensionException, org.nlogo.api.LogoException {
    try {
      String soundpath = args[0].getString();
      File soundFile;
      soundpath = context.attachCurrentDirectory(soundpath);

      try {
        soundFile = new File(context.attachCurrentDirectory(soundpath));
      } catch (java.net.MalformedURLException ex) {
        soundFile = new File(soundpath);
      }
      SoundExtension.LoopSound(soundFile);

    } catch (java.net.MalformedURLException ex) {
      throw new org.nlogo.api.ExtensionException
          ("Unable to open sound sample: " + ex.getMessage());
    }
  }
}
