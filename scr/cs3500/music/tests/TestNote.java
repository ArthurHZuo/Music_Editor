import org.junit.Test;

import cs3500.music.model.NoteBuilderImp;
import cs3500.music.model.NoteModel;
import cs3500.music.model.Pitch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
Tests for Note
*/

public class TestNote {
  NoteModel n1 = new NoteBuilderImp().setStart(1).setEnd(5).setPitch(24).build();
  NoteModel n2 = new NoteBuilderImp().setStart(1).setEnd(2).setPitch(24).build();
  NoteModel n3 = new NoteBuilderImp().setStart(2).setEnd(4).setPitch(24).build();
  NoteModel n4 = new NoteBuilderImp().setStart(1).setEnd(5).setPitch(48).build();
  NoteModel n5 = new NoteBuilderImp().setStart(1).setEnd(5).setPitch(39).build();
  NoteModel n6 = new NoteBuilderImp().setStart(5).setEnd(14).setPitch(48).build();
  NoteModel n7 = new NoteBuilderImp().setStart(12).setEnd(13).setPitch(48).build();

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException1() {
    new NoteBuilderImp().setStart(-1).setEnd(5).setPitch(24).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException2() {
    new NoteBuilderImp().setStart(-5).setEnd(5).setPitch(24).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException3() {
    new NoteBuilderImp().setStart(1).setEnd(5).setPitch(-2).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException4() {
    new NoteBuilderImp().setStart(1).setEnd(5).setPitch(140).build();
  }

  @Test(expected = IllegalStateException.class)
  public void testConstructorException5() {
    new NoteBuilderImp().setStart(1).setEnd(0).setPitch(104).build();
  }

  @Test(expected = IllegalStateException.class)
  public void testConstructorException6() {
    new NoteBuilderImp().setStart(5).setEnd(2).setPitch(104).build();
  }

  @Test
  public void testGetBeat() {
    assertEquals(n1.getBeat(), 1);
    assertEquals(n2.getBeat(), 1);
    assertEquals(n3.getBeat(), 2);
    assertEquals(n4.getBeat(), 1);
    assertEquals(n5.getBeat(), 1);
    assertEquals(n6.getBeat(), 5);
    assertEquals(n7.getBeat(), 12);
  }

  @Test
  public void testGetPitch() {
    assertEquals(n1.getPitch(), Pitch.C);
    assertEquals(n2.getPitch(), Pitch.C);
    assertEquals(n3.getPitch(), Pitch.C);
    assertEquals(n4.getPitch(), Pitch.C);
    assertEquals(n5.getPitch(), Pitch.DSHARP);
    assertEquals(n6.getPitch(), Pitch.C);
    assertEquals(n7.getPitch(), Pitch.C);
  }

  @Test
  public void testGetOctave() {
    assertEquals(n1.getOctave(), 1);
    assertEquals(n2.getOctave(), 1);
    assertEquals(n3.getOctave(), 1);
    assertEquals(n4.getOctave(), 3);
    assertEquals(n5.getOctave(), 2);
    assertEquals(n6.getOctave(), 3);
    assertEquals(n7.getOctave(), 3);
  }

  @Test
  public void testGetDuration() {
    assertEquals(n1.getDuration(), 4);
    assertEquals(n2.getDuration(), 1);
    assertEquals(n3.getDuration(), 2);
    assertEquals(n4.getDuration(), 4);
    assertEquals(n5.getDuration(), 4);
    assertEquals(n6.getDuration(), 9);
    assertEquals(n7.getDuration(), 1);
  }

  @Test
  public void testEquals() {
    assertTrue(n1.equals(new NoteBuilderImp().setStart(1).setEnd(5).setPitch(24).build()));
    assertFalse(n1.equals(n2));
    assertFalse(n1.equals(n3));
    assertFalse(n1.equals(n4));
    assertFalse(n1.equals(n5));
    assertFalse(n1.equals(n6));
    assertFalse(n1.equals(n7));
    assertFalse(n3.equals(n4));
    assertTrue(n1.equals(n1));
    assertTrue(n5.equals(new NoteBuilderImp().setStart(1).setEnd(5).setPitch(39).build()));
  }

  @Test
  public void testComparePitch() {
    assertTrue(n1.comparePitch(n2) == 0);
    assertTrue(n1.comparePitch(n3) == 0);
    assertTrue(n2.comparePitch(n3) == 0);
    assertTrue(n1.comparePitch(n1) == 0);
    assertTrue(n4.comparePitch(n2) < 0);
    assertTrue(n2.comparePitch(n4) > 0);
    assertTrue(n6.comparePitch(n7) == 0);
    assertTrue(n6.comparePitch(n4) == 0);
    assertTrue(n5.comparePitch(n1) < 0);
    assertTrue(n5.comparePitch(n7) > 0);


  }

  @Test(expected = IllegalArgumentException.class)
  public void testComparePitchException() {
    n1.comparePitch(null);
  }
}
