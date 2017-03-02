package cs3500.music.util;


import cs3500.music.otherModel.Pitch;

/**
 * Created by Spencer on 12/7/15.
 * An adapter class to convert from one Pitch enum to the other.
 */
public class AdaptPitch {

  /**
   * Adapts a given pitch from the otherModel to our Model's pitch by mapping the
   * given numerical values, which remain constant across applications, to our
   * alphanumerical definitions of each Pitch.
   * @param otherPitch the pitch to adapt
   * @return the adapted pitch as our model uses it.
   */
  public static cs3500.music.model.Pitch adaptTheirPitch(cs3500.music.otherModel.Pitch otherPitch) {

    switch (cs3500.music.otherModel.Pitch.getIntValue(otherPitch)) {

      case 0:
        return cs3500.music.model.Pitch.C;
      case 1:
        return cs3500.music.model.Pitch.CSHARP;
      case 2:
        return cs3500.music.model.Pitch.D;
      case 3:
        return cs3500.music.model.Pitch.DSHARP;
      case 4:
        return cs3500.music.model.Pitch.E;
      case 5:
        return cs3500.music.model.Pitch.F;
      case 6:
        return cs3500.music.model.Pitch.FSHARP;
      case 7:
        return cs3500.music.model.Pitch.G;
      case 8:
        return cs3500.music.model.Pitch.GSHARP;
      case 9:
        return cs3500.music.model.Pitch.A;
      case 10:
        return cs3500.music.model.Pitch.ASHARP;
      case 11:
        return cs3500.music.model.Pitch.B;
      default:
        throw new IllegalArgumentException("Something went very wrong.");
    }
  }

  /**
   * Adapts our Pitch to their Pitch by using the getPitch method.
   *
   * @param ourPitch the Pitch to be adapted
   * @return the new Pitch in their format.
   */
  public static cs3500.music.otherModel.Pitch adaptOurPitch(cs3500.music.model.Pitch ourPitch) {
    return Pitch.getPitch(ourPitch.getMidiPitch(1));
  }

}