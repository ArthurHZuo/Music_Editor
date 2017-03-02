import org.junit.Test;

import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Pitch
 */

public class TestPitch {
  @Test
  public void testToString() {
    assertEquals(Pitch.A.toString(), "A");
    assertEquals(Pitch.ASHARP.toString(), "A♯");
    assertEquals(Pitch.B.toString(), "B");
    assertEquals(Pitch.C.toString(), "C");
    assertEquals(Pitch.CSHARP.toString(), "C♯");
    assertEquals(Pitch.D.toString(), "D");
    assertEquals(Pitch.DSHARP.toString(), "D♯");
    assertEquals(Pitch.E.toString(), "E");
    assertEquals(Pitch.F.toString(), "F");
    assertEquals(Pitch.FSHARP.toString(), "F♯");
    assertEquals(Pitch.G.toString(), "G");
    assertEquals(Pitch.GSHARP.toString(), "G♯");
  }

  @Test
  public void testMidiPitch() {
    assertEquals(Pitch.C.getMidiPitch(0), 12);
    assertEquals(Pitch.CSHARP.getMidiPitch(0), 13);
    assertEquals(Pitch.C.getMidiPitch(1), 24);
    assertEquals(Pitch.CSHARP.getMidiPitch(1), 25);
    assertEquals(Pitch.A.getMidiPitch(0), 21);
    assertEquals(Pitch.A.getMidiPitch(4), 69);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooLowOctave() {
    Pitch.B.getMidiPitch(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTooHighOctave() {
    Pitch.B.getMidiPitch(9);
  }
}
