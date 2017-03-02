package cs3500.music.otherModel;

import cs3500.music.otherView.OtherConcreteGuiViewPanel;

/**
 * Represents the 12 possible pitches that can be given to the music editor at each octave
 *
 * An "S" carries the same value as "#" i.e AS = A# = A Sharp = The black piano key next to A on a
 * piano
 */
public enum Pitch {

  A(9, "A"),
  AS(10, "A#"),
  B(11, "B"),
  C(0, "C"),
  CS(1, "C#"),
  D(2, "D"),
  DS(3, "D#"),
  E(4, "E"),
  F(5, "F"),
  FS(6, "F#"),
  G(7, "G"),
  GS(8, "G#");

  // Represents value of pitch on scale of [0,12]
  private final int value;

  // Represents String version of pitch
  private final String str;

  // Basic constructor for value
  Pitch(int value, String str) {
    this.value = value;
    this.str = str;
  }

  /**
   * Returns the int value of the given pitch
   *
   * @param p represents the pitch whose value is needed
   * @return the int value of the pitch
   */
  public static int getIntValue(Pitch p) {

    return p.value;
  }

  /**
   * Returns the String value of a Pitch + Octave
   *
   * @param p represents the pitch (out of 128)
   * @return a full String version of the pitch
   */
  public static String pitchToString(int p) {

    int pitchVal = p % 12;
    int oct = p / 11;
    Pitch out = A;
    for (Pitch p1 : Pitch.values()) {

      if (pitchVal == p1.value) {
        out = p1;
      }

    }
    return out.str + oct;

  }

  /**
   * Takes an int from [0,127] and determines what Pitch it represents Ex: 0 represents Pitch.C Ex:
   * 3 represents Pitch.DS
   *
   * @param p is an int value for a pitch
   * @returns a Pitch value
   */
  public static Pitch getPitch(int p) {

    // Reduces input to one of the 13 pitch values
    int num = p % 12;

    switch (num) {
      case 0:
        return Pitch.C;
      case 1:
        return Pitch.CS;
      case 2:
        return Pitch.D;
      case 3:
        return Pitch.DS;
      case 4:
        return Pitch.E;
      case 5:
        return Pitch.F;
      case 6:
        return Pitch.FS;
      case 7:
        return Pitch.G;
      case 8:
        return Pitch.GS;
      case 9:
        return Pitch.A;
      case 10:
        return Pitch.AS;
      case 11:
        return Pitch.B;
      default:
        throw new IllegalArgumentException("Please input actual Pitch");
    }

  }

  /**
   * Outputs a Pitch value based on a Y-value
   *
   * @param Y   represents the Y value to be converted
   * @param low represents the lowest note of the board
   * @return the Pitch of the Y-value.
   */
  public static Pitch getPitchFromY(int Y, ANote low) {

    int PitchVal = low.intValue() +
        (OtherConcreteGuiViewPanel.bottom - Y) / OtherConcreteGuiViewPanel.rectSize;

    PitchVal = PitchVal + PitchVal / 11 + 1;

    System.out.println(PitchVal);
    System.out.println("Bottom: " + OtherConcreteGuiViewPanel.bottom);


    return Pitch.getPitch(PitchVal);
  }
}