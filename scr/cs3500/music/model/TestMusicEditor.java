package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for MusicComposition
 */

public class TestMusicEditor {

  MusicComposition me1; //Empty
  MusicComposition me2; //few notes

  private void init() {
    this.me1 = MusicComposition.emptyEditor(100);
    this.me2 = MusicComposition.populatedEditor(100,
        new Note(4, Pitch.C, 1, 1, 100, 1),
        new Note(4, Pitch.FSHARP, 1, 1, 100, 4),
        new Note(16, Pitch.C, 1, 1, 10, 3),
        new Note(2, Pitch.C, 1, 2, 100, 1),
        new Note(4, Pitch.D, 4, 1, 100, 1),
        new Note(1, Pitch.GSHARP, 2, 1, 100, 1),
        new Note(3, Pitch.A, 1, 10, 100, 1),
        new Note(4, Pitch.C, 1, 1, 100, 1));
  }

  @Test
  public void testAddNote() {
    init();
    assertEquals(this.me1.lastBeat(), 0);
    me1.addNote(new Note(1, Pitch.C, 1, 1, 100, 2));
    assertEquals(this.me1.lastBeat(), 1);
    me1.addNote(new Note(10, Pitch.ASHARP, 2, 10, 100, 3));
    assertEquals(this.me1.lastBeat(), 19);
    Note n1 = new Note(5, Pitch.FSHARP, 5, 3, 50, 4);
    me1.addNote(n1);
    assertTrue(me1.getNotesStartingAt(3).contains(n1));
    assertTrue(me1.getNotesPlayingAt(3).contains(n1));
    assertTrue(me1.getNotesPlayingAt(7).contains(n1));
    me1.addNote(n1); //Add note twice
    assertTrue(me1.getNotesStartingAt(3).contains(n1));
    assertTrue(me1.getNotesPlayingAt(3).contains(n1));
    assertTrue(me1.getNotesPlayingAt(5).contains(n1));
    assertTrue(me1.getNotesPlayingAt(7).contains(n1));
    assertFalse(me2.getNotesStartingAt(3).contains(n1));
    assertFalse(me2.getNotesPlayingAt(3).contains(n1));
    assertFalse(me2.getNotesPlayingAt(5).contains(n1));
    assertFalse(me2.getNotesPlayingAt(7).contains(n1));
    me2.addNote(n1);
    assertTrue(me2.getNotesStartingAt(3).contains(n1));
    assertTrue(me2.getNotesPlayingAt(3).contains(n1));
    assertTrue(me2.getNotesPlayingAt(5).contains(n1));
    assertTrue(me2.getNotesPlayingAt(7).contains(n1));
    Note n2 = new Note(3, Pitch.FSHARP, 5, 4, 10, 1);
    this.me2.addNote(n2);
    assertTrue(me2.getNotesStartingAt(4).contains(n2));
    assertTrue(me2.getNotesPlayingAt(4).contains(n2));
    assertTrue(me2.getNotesPlayingAt(5).contains(n2));
    assertTrue(me2.getNotesPlayingAt(6).contains(n2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNoteException() {
    init();
    this.me1.addNote(null);
  }

  @Test
  public void testRemoveNote() {
    init();
    assertEquals(me1.lastBeat(), 0);
    Note n1 = new Note(5, Pitch.FSHARP, 5, 3, 10, 10);
    assertFalse(me1.getNotesStartingAt(3).contains(n1));
    assertFalse(me1.getNotesPlayingAt(3).contains(n1));
    assertFalse(me1.getNotesPlayingAt(5).contains(n1));
    assertFalse(me1.getNotesPlayingAt(7).contains(n1));
    me1.addNote(n1);
    assertTrue(me1.getNotesStartingAt(3).contains(n1));
    assertTrue(me1.getNotesPlayingAt(3).contains(n1));
    assertTrue(me1.getNotesPlayingAt(5).contains(n1));
    assertTrue(me1.getNotesPlayingAt(7).contains(n1));
    me1.removeNote(n1);
    assertFalse(me1.getNotesStartingAt(3).contains(n1));
    assertFalse(me1.getNotesPlayingAt(3).contains(n1));
    assertFalse(me1.getNotesPlayingAt(5).contains(n1));
    assertFalse(me1.getNotesPlayingAt(7).contains(n1));
    me1.addNote(n1);
    me1.addNote(new Note(5, Pitch.GSHARP, 3, 40, 100, 18));
    me1.addNote(new Note(10, Pitch.FSHARP, 2, 2, 100, 20));
    assertTrue(me1.getNotesStartingAt(40).contains(new Note(5, Pitch.GSHARP, 3, 40, 100, 18)));
    assertTrue(me1.getNotesPlayingAt(40).contains(new Note(5, Pitch.GSHARP, 3, 40, 100, 18)));
    assertTrue(me1.getNotesPlayingAt(43).contains(new Note(5, Pitch.GSHARP, 3, 40, 100, 18)));
    assertTrue(me1.getNotesPlayingAt(44).contains(new Note(5, Pitch.GSHARP, 3, 40, 100, 18)));
    me1.removeNote(new Note(5, Pitch.GSHARP, 3, 40, 100, 18));
    assertFalse(me1.getNotesStartingAt(40).contains(new Note(5, Pitch.GSHARP, 3, 40, 100, 18)));
    assertFalse(me1.getNotesPlayingAt(40).contains(new Note(5, Pitch.GSHARP, 3, 40, 100, 18)));
    assertFalse(me1.getNotesPlayingAt(43).contains(new Note(5, Pitch.GSHARP, 3, 40, 100, 18)));
    assertFalse(me1.getNotesPlayingAt(44).contains(new Note(5, Pitch.GSHARP, 3, 40, 100, 18)));
    assertEquals(me2.lastBeat(), 16);
    me2.removeNote(new Note(16, Pitch.C, 1, 1, 10, 3));
    assertEquals(me2.lastBeat(), 12);
    Note n2 = new Note(1, Pitch.ASHARP, 2, 3, 10, 1);
    assertFalse(me1.getNotesStartingAt(3).contains(n2));
    me1.addNote(n2);
    me1.addNote(n2);
    assertTrue(me1.getNotesStartingAt(3).contains(n2));
    me1.removeNote(n2);
    assertTrue(me1.getNotesStartingAt(3).contains(n2)); //Only removes the first instance of n2
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNoteException1() {
    init();
    this.me2.removeNote(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNoteException2() {
    init();
    this.me1.removeNote(new Note(4, Pitch.C, 1, 1, 110, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNoteException3() {
    init();
    this.me2.removeNote(new Note(4, Pitch.C, 7, 1, 110, 1));
  }

  @Test
  public void testEditNote() {
    init();
    assertEquals(me1.lastBeat(), 0);
    me1.addNote(new Note(4, Pitch.C, 7, 1, 75, 3));
    assertEquals(me1.lastBeat(), 4);
    me1.editNote(new Note(4, Pitch.C, 7, 1, 75, 3), new Note(7, Pitch.D, 7, 12, 75, 3));
    assertEquals(me1.lastBeat(), 18);
    assertTrue(me2.getNotesStartingAt(2).contains(new Note(2, Pitch.C, 1, 2, 100, 1)));
    assertFalse(me2.getNotesStartingAt(15).contains(new Note(15, Pitch.F, 3, 15, 50, 3)));
    me2.editNote(new Note(2, Pitch.C, 1, 2, 100, 1), new Note(15, Pitch.F, 3, 15, 50, 3));
    assertFalse(me2.getNotesStartingAt(2).contains(new Note(2, Pitch.C, 1, 2, 100, 1)));
    assertTrue(me2.getNotesStartingAt(15).contains(new Note(15, Pitch.F, 3, 15, 50, 3)));
    Note n2 = new Note(1, Pitch.ASHARP, 2, 3, 60, 4);
    assertFalse(me1.getNotesStartingAt(3).contains(n2));
    me1.addNote(n2);
    me1.addNote(n2);
    Note n3 = new Note(15, Pitch.GSHARP, 3, 15, 70, 5);
    assertFalse(me1.getNotesStartingAt(15).contains(n3));
    assertTrue(me1.getNotesStartingAt(3).contains(n2));
    me1.editNote(n2, n3); //Only changes one instance of the note that was added twice.
    assertTrue(me1.getNotesStartingAt(15).contains(n3));
    assertTrue(me1.getNotesStartingAt(3).contains(n2));


  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditNoteException1() {
    init();
    me2.editNote(null, new Note(4, Pitch.FSHARP, 1, 1, 100, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditNoteException2() {
    init();
    me2.editNote(new Note(2, Pitch.C, 1, 2, 100, 1), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditNoteException3() {
    init();
    me2.editNote(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEditNoteException4() {
    init();
    me2.editNote(new Note(12, Pitch.C, 1, 2, 100, 1), new Note(2, Pitch.FSHARP, 1, 1, 100, 1));
  }

  @Test
  public void testGetNotesPlayingAt() {
    init();
    assertEquals(me1.getNotesPlayingAt(1).size(), 0);
    assertEquals(me1.getNotesPlayingAt(5).size(), 0);
    me1.addNote(new Note(6, Pitch.FSHARP, 2, 1, 50, 4));
    assertEquals(me1.getNotesPlayingAt(1).size(), 1);
    assertEquals(me1.getNotesPlayingAt(5).size(), 1);
    assertEquals(me1.getNotesPlayingAt(2).size(), 1);
    assertEquals(me1.getNotesPlayingAt(4).size(), 1);
    assertTrue(me1.getNotesPlayingAt(1).contains(new Note(6, Pitch.FSHARP, 2, 1, 50, 4)));
    assertTrue(me1.getNotesPlayingAt(3).contains(new Note(6, Pitch.FSHARP, 2, 1, 50, 4)));
    assertTrue(me1.getNotesPlayingAt(6).contains(new Note(6, Pitch.FSHARP, 2, 1, 50, 4)));
    me1.addNote(new Note(3, Pitch.D, 1, 2, 60, 3));
    assertEquals(me1.getNotesPlayingAt(2).size(), 2);
    assertEquals(me1.getNotesPlayingAt(4).size(), 2);
    assertEquals(me1.getNotesPlayingAt(1).size(), 1);
    assertEquals(me1.getNotesPlayingAt(5).size(), 1);
    assertTrue(me1.getNotesPlayingAt(2).contains(new Note(3, Pitch.D, 1, 2, 60, 3)));
    assertTrue(me1.getNotesPlayingAt(4).contains(new Note(3, Pitch.D, 1, 2, 60, 3)));
    assertTrue(me1.getNotesPlayingAt(3).contains(new Note(3, Pitch.D, 1, 2, 60, 3)));
    assertTrue(me1.getNotesPlayingAt(2).contains(new Note(6, Pitch.FSHARP, 2, 1, 50, 4)));
    assertTrue(me1.getNotesPlayingAt(3).contains(new Note(6, Pitch.FSHARP, 2, 1, 50, 4)));
    assertTrue(me1.getNotesPlayingAt(4).contains(new Note(6, Pitch.FSHARP, 2, 1, 50, 4)));
    assertEquals(me2.getNotesPlayingAt(2).size(), 6);
    assertTrue(me2.getNotesPlayingAt(2).contains(new Note(2, Pitch.C, 1, 2, 100, 1)));
    assertFalse(me2.getNotesPlayingAt(2).contains(new Note(1, Pitch.GSHARP, 2, 1, 100, 1)));
    assertEquals(me2.getNotesPlayingAt(1).size(), 6);
    assertFalse(me2.getNotesPlayingAt(1).contains(new Note(2, Pitch.C, 1, 2, 100, 1)));
    assertTrue(me2.getNotesPlayingAt(1).contains(new Note(1, Pitch.GSHARP, 2, 1, 100, 1)));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNotesPlayingAtException1() {
    init();
    me1.getNotesPlayingAt(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNotesPlayingAtException2() {
    init();
    me2.getNotesPlayingAt(-1);
  }

  @Test
  public void testGetNotesStartingAt() {
    init();
    assertEquals(me1.getNotesStartingAt(1).size(), 0);
    assertEquals(me1.getNotesStartingAt(5).size(), 0);
    me1.addNote(new Note(6, Pitch.FSHARP, 2, 1, 50, 4));
    assertEquals(me1.getNotesStartingAt(1).size(), 1);
    assertEquals(me1.getNotesStartingAt(5).size(), 0);
    assertTrue(me1.getNotesStartingAt(1).contains(new Note(6, Pitch.FSHARP, 2, 1, 50, 4)));
    me1.addNote(new Note(6, Pitch.FSHARP, 2, 5, 60, 5));
    assertTrue(me1.getNotesStartingAt(1).contains(new Note(6, Pitch.FSHARP, 2, 1, 50, 4)));
    assertTrue(me1.getNotesStartingAt(5).contains(new Note(6, Pitch.FSHARP, 2, 5, 60, 5)));
    assertEquals(me1.getNotesStartingAt(1).size(), 1);
    assertEquals(me1.getNotesStartingAt(5).size(), 1);
    me1.addNote(new Note(2, Pitch.GSHARP, 5, 5, 70, 7));
    assertTrue(me1.getNotesStartingAt(1).contains(new Note(6, Pitch.FSHARP, 2, 1, 50, 4)));
    assertTrue(me1.getNotesStartingAt(5).contains(new Note(6, Pitch.FSHARP, 2, 5, 60, 5)));
    assertTrue(me1.getNotesStartingAt(5).contains(new Note(2, Pitch.GSHARP, 5, 5, 70, 7)));
    assertEquals(me1.getNotesStartingAt(5).size(), 2);
    assertEquals(me2.getNotesStartingAt(1).size(), 6);
    assertEquals(me2.getNotesStartingAt(2).size(), 1);
    me2.editNote(new Note(4, Pitch.C, 1, 1, 100, 1), new Note(4, Pitch.C, 1, 2, 10, 12));
    assertEquals(me2.getNotesStartingAt(2).size(), 2);
    assertEquals(me2.getNotesStartingAt(1).size(), 5);
    me2.removeNote(new Note(4, Pitch.D, 4, 1, 100, 1));
    assertEquals(me2.getNotesStartingAt(2).size(), 2);
    assertEquals(me2.getNotesStartingAt(1).size(), 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNotesStartingAtException1() {
    init();
    me1.getNotesStartingAt(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNotesStartingAtException2() {
    init();
    me2.getNotesStartingAt(-1);
  }

  @Test
  public void lastBeatTest() {
    init();
    assertEquals(me1.lastBeat(), 0);
    me1.addNote(new Note(1, Pitch.F, 2, 1, 10, 12));
    assertEquals(me1.lastBeat(), 1);
    me1.addNote(new Note(1, Pitch.D, 1, 1, 12, 14));
    assertEquals(me1.lastBeat(), 1);
    me1.addNote(new Note(3, Pitch.CSHARP, 5, 3, 127, 70));
    assertEquals(me1.lastBeat(), 5);
    assertEquals(me2.lastBeat(), 16);
    me2.addNote(new Note(3, Pitch.FSHARP, 6, 100, 50, 14));
    assertEquals(me2.lastBeat(), 102);
  }

  @Test
  public void lowestNoteTest() {
    init();
    me1.addNote(new Note(1, Pitch.F, 2, 1, 100, 5));
    assertEquals(me1.lowestNote(), new Note(1, Pitch.F, 2, 1, 100, 5));
    me1.addNote(new Note(1, Pitch.F, 3, 1, 100, 5));
    assertEquals(me1.lowestNote(), new Note(1, Pitch.F, 2, 1, 100, 5));
    me1.editNote(new Note(1, Pitch.F, 3, 1, 100, 5), new Note(1, Pitch.ASHARP, 2, 1, 100, 5));
    assertEquals(me1.lowestNote(), new Note(1, Pitch.F, 2, 1, 100, 5));
    me1.editNote(new Note(1, Pitch.ASHARP, 2, 1, 100, 5), new Note(1, Pitch.F, 4, 1, 100, 5));
    assertEquals(me1.lowestNote(), new Note(1, Pitch.F, 2, 1, 100, 5));
    me1.addNote(new Note(1, Pitch.A, 1, 1, 100, 5));
    assertEquals(me1.lowestNote(), new Note(1, Pitch.A, 1, 1, 100, 5));
    assertEquals(me2.lowestNote(), new Note(4, Pitch.C, 1, 1, 100, 1));
    me2.editNote(new Note(4, Pitch.C, 1, 1, 100, 1), new Note(2, Pitch.C, 1, 1, 100, 5));
    me2.removeNote(new Note(4, Pitch.C, 1, 1, 100, 1));
    assertEquals(me2.lowestNote(), new Note(16, Pitch.C, 1, 1, 10, 3));
    me2.removeNote(new Note(16, Pitch.C, 1, 1, 10, 3));
    me2.addNote(new Note(1, Pitch.C, 2, 50, 100, 5));
    assertEquals(me2.lowestNote(), new Note(2, Pitch.C, 1, 1, 100, 5));
  }

  @Test(expected = IllegalStateException.class)
  public void lowestNoteTestException() {
    init();
    me1.lowestNote();
  }

  @Test
  public void highestNoteTest() {
    init();
    me1.addNote(new Note(1, Pitch.F, 2, 1, 100, 5));
    assertEquals(me1.highestNote(), new Note(1, Pitch.F, 2, 1, 100, 5));
    me1.addNote(new Note(1, Pitch.A, 1, 1, 100, 5));
    assertEquals(me1.highestNote(), new Note(1, Pitch.F, 2, 1, 100, 5));
    me1.addNote(new Note(1, Pitch.G, 2, 1, 100, 5));
    assertEquals(me1.highestNote(), new Note(1, Pitch.G, 2, 1, 100, 5));
    me1.editNote(new Note(1, Pitch.G, 2, 1, 100, 5), new Note(1, Pitch.A, 2, 1, 100, 5));
    assertEquals(me1.highestNote(), new Note(1, Pitch.A, 2, 1, 100, 5));
    me1.addNote(new Note(1, Pitch.G, 4, 1, 100, 5));
    assertEquals(me1.highestNote(), new Note(1, Pitch.G, 4, 1, 100, 5));
    assertEquals(me2.highestNote(), new Note(4, Pitch.D, 4, 1, 100, 1));
    me2.removeNote(new Note(4, Pitch.D, 4, 1, 100, 1));
    assertEquals(me2.highestNote(), new Note(1, Pitch.GSHARP, 2, 1, 100, 1));
    me2.addNote(new Note(1, Pitch.GSHARP, 1, 1, 100, 5));
    assertEquals(me2.highestNote(), new Note(1, Pitch.GSHARP, 2, 1, 100, 1));
    me2.addNote(new Note(1, Pitch.GSHARP, 2, 10, 100, 5));
    assertEquals(me2.highestNote(), new Note(1, Pitch.GSHARP, 2, 1, 100, 1));
    me2.removeNote(new Note(1, Pitch.GSHARP, 2, 1, 100, 1));
    assertEquals(me2.highestNote(), new Note(1, Pitch.GSHARP, 2, 10, 100, 5));
    me2.addNote(new Note(1, Pitch.GSHARP, 2, 1, 100, 5));
    assertEquals(me2.highestNote(), new Note(1, Pitch.GSHARP, 2, 1, 100, 5));
    me2.addNote(new Note(1, Pitch.ASHARP, 3, 1, 100, 5));
    assertEquals(me2.highestNote(), new Note(1, Pitch.ASHARP, 3, 1, 100, 5));
    me2.addNote(new Note(1, Pitch.A, 3, 1, 100, 5));
    assertEquals(me2.highestNote(), new Note(1, Pitch.ASHARP, 3, 1, 100, 5));
  }


  @Test(expected = IllegalStateException.class)
  public void highestNoteTestException() {
    init();
    me1.highestNote();
  }
}
