import org.junit.Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;

import cs3500.music.controller.MusicEditorController;
import cs3500.music.model.MusicCompositionBuilder;
import cs3500.music.model.MusicEditorControllerModel;
import cs3500.music.util.MockGuiView;
import cs3500.music.util.MusicReader;

import static org.junit.Assert.assertEquals;

/**
 * Tests to check if Controller handles Key and Mouse Events Properly.
 */
public class TestHandlers {

  MusicEditorControllerModel model1;
  MusicEditorController cont1;
  MusicEditorControllerModel model2;
  MusicEditorController cont2;
  KeyListener kl1;
  KeyListener kl2;
  MouseListener ml1;
  MouseListener ml2;

  void init() throws FileNotFoundException {
    this.model1 = new MusicCompositionBuilder().build();
    MockGuiView view1 = new MockGuiView();
    //this.cont1 = new Controller(view1, model1);
    this.kl1 = view1.getKeyHandler();
    this.ml1 = view1.getMouseHandler();
    this.model2 = MusicReader.parseFile(
        new FileReader("mary-first6.txt"), new MusicCompositionBuilder());
    MockGuiView view2 = new MockGuiView();
    //this.cont2 = new Controller(view2, model2);
    this.kl2 = view2.getKeyHandler();
    this.ml2 = view2.getMouseHandler();
  }

  @Test
  public void testKeyHandlerReset() throws FileNotFoundException {
    init();
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_R, 'r'));
    assertEquals(model2.lastBeat(), 0);

    init();
    assertEquals(model2.getNotesPlayingAt(2).size(), 2);
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_R, 'r'));
    assertEquals(model2.getNotesPlayingAt(2).size(), 0);
    kl1.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl1.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(model1.getCurrentBeat(), 2);
    kl1.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_HOME, KeyEvent.CHAR_UNDEFINED));
    assertEquals(model1.getCurrentBeat(), 0);
  }

  @Test
  public void testKeyHandlerHome() throws FileNotFoundException {
    init();
    assertEquals(0, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(1, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_HOME, KeyEvent.CHAR_UNDEFINED));
    assertEquals(0, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_HOME, KeyEvent.CHAR_UNDEFINED));
    assertEquals(0, model2.getCurrentBeat());
    assertEquals(0, model1.getCurrentBeat());
    kl1.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl1.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(2, model1.getCurrentBeat());
    kl1.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_HOME, KeyEvent.CHAR_UNDEFINED));
    assertEquals(0, model1.getCurrentBeat());
  }

  @Test
  public void testKeyHandlerEnd() throws FileNotFoundException {
    init();
    assertEquals(0, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(1, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_END, KeyEvent.CHAR_UNDEFINED));
    assertEquals(8, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(9, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_END, KeyEvent.CHAR_UNDEFINED));
    assertEquals(8, model2.getCurrentBeat());
    init();
    assertEquals(0, model1.getCurrentBeat());
    kl1.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_END, KeyEvent.CHAR_UNDEFINED));
    assertEquals(1, model1.getCurrentBeat());
    kl1.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl1.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(3, model1.getCurrentBeat());
    kl1.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_END, KeyEvent.CHAR_UNDEFINED));
    assertEquals(1, model1.getCurrentBeat());
  }

  @Test
  public void testKeyHandlerRight() throws FileNotFoundException {
    init();
    assertEquals(0, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(1, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(4, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_END, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(11, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_HOME, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(4, model2.getCurrentBeat());

  }

  @Test
  public void testKeyHandlerLeft() throws FileNotFoundException {
    init();
    assertEquals(0, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(0, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_END, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(3, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_END, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(7, model2.getCurrentBeat());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_HOME, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));
    assertEquals(0, model2.getCurrentBeat());
  }

  @Test
  public void testMouseHandlerCreate() throws FileNotFoundException {
    init();
    assertEquals(model2.getNotesPlayingAt(55).size(), 0);
    ml2.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 55, 55, 1, false, MouseEvent.BUTTON1));
    ml2.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 100, 55, 1, false, MouseEvent.BUTTON1));
    assertEquals(model2.getNotesPlayingAt(55).size(), 1);
    ml2.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 52, 55, 1, false, MouseEvent.BUTTON1));
    ml2.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 59, 55, 1, false, MouseEvent.BUTTON1));
    assertEquals(model2.getNotesPlayingAt(55).size(), 2);
    assertEquals(2, model2.getNotesPlayingAt(58).size());
    assertEquals(1, model2.getNotesPlayingAt(59).size());
    assertEquals(1, model2.getNotesStartingAt(52).size());
    ml2.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 52, 65, 1, false, MouseEvent.BUTTON1));
    ml2.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 59, 65, 1, false, MouseEvent.BUTTON1));
    assertEquals(2, model2.getNotesStartingAt(52).size());

    assertEquals(0, model1.getNotesStartingAt(55).size());
    ml1.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 55, 55, 1, false, MouseEvent.BUTTON1));
    ml1.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 85, 55, 1, false, MouseEvent.BUTTON1));
    assertEquals(1, model1.getNotesStartingAt(55).size());
  }

  @Test public void testMouseHandlerRemove() throws FileNotFoundException {
    init();
    assertEquals(2, model2.getNotesPlayingAt(2).size());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_SHIFT, KeyEvent.CHAR_UNDEFINED));
    ml2.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 2, 31, 1, false, MouseEvent.BUTTON1));
    ml2.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 2, 31, 1, false, MouseEvent.BUTTON1));
    assertEquals(1, model2.getNotesPlayingAt(2).size());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_SHIFT, KeyEvent.CHAR_UNDEFINED));
    ml2.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 2, 38, 1, false, MouseEvent.BUTTON1));
    ml2.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 2, 38, 1, false, MouseEvent.BUTTON1));
    assertEquals(0, model2.getNotesPlayingAt(2).size());
    init();
    assertEquals(2, model2.getNotesPlayingAt(2).size());
    kl2.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_SHIFT, KeyEvent.CHAR_UNDEFINED));
    ml2.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 2, 44, 1, false, MouseEvent.BUTTON1));
    ml2.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 2, 44, 1, false, MouseEvent.BUTTON1));
    assertEquals(2, model2.getNotesPlayingAt(2).size());

    assertEquals(0, model1.getNotesPlayingAt(0).size());
    kl1.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_SHIFT, KeyEvent.CHAR_UNDEFINED));
    ml1.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 0, 44, 1, false, MouseEvent.BUTTON1));
    ml1.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 0, 44, 1, false, MouseEvent.BUTTON1));
    assertEquals(0, model1.getNotesPlayingAt(0).size());

    assertEquals(0, model1.getNotesPlayingAt(0).size());
    ml1.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 0, 44, 1, false, MouseEvent.BUTTON1));
    ml1.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 5, 41, 1, false, MouseEvent.BUTTON1));
    assertEquals(0, model1.getNotesPlayingAt(0).size());
  }

  @Test public void testMouseHandlerMove() throws FileNotFoundException {
    init();
    assertEquals(2, model2.getNotesPlayingAt(2).size());
    ml2.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 0, 40, 1, false, MouseEvent.BUTTON1));
    ml2.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 2, 40, 1, false, MouseEvent.BUTTON1));
    assertEquals(3, model2.getNotesPlayingAt(2).size());
    assertEquals(2, model2.getNotesStartingAt(2).size());
    assertEquals(0, model2.getNotesPlayingAt(100).size());
    assertEquals(7, model2.lastBeat());
    ml2.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 3, 40, 1, false, MouseEvent.BUTTON1));
    ml2.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 99, 40, 1, false, MouseEvent.BUTTON1));
    assertEquals(1, model2.getNotesPlayingAt(100).size());
    assertEquals(100, model2.lastBeat());

    assertEquals(0, model1.getNotesPlayingAt(0).size());
    ml1.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 0, 44, 1, false, MouseEvent.BUTTON1));
    ml1.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 5, 41, 1, false, MouseEvent.BUTTON1));
    assertEquals(1, model1.getNotesPlayingAt(0).size());
    ml1.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 0, 41, 1, false, MouseEvent.BUTTON1));
    ml1.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 17, 41, 1, false, MouseEvent.BUTTON1));
    assertEquals(0, model1.getNotesPlayingAt(0).size());
    assertEquals(1, model1.getNotesPlayingAt(17).size());
  }

  @Test public void testSpecifyInstrument() throws FileNotFoundException {
    init();
    assertEquals(0, model1.getNotesPlayingAt(0).size());
    kl1.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_9, '9'));
    ml1.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 55, 55, 1, false, MouseEvent.BUTTON1));
    ml1.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 85, 55, 1, false, MouseEvent.BUTTON1));
    assertEquals(10, model1.getNotesPlayingAt(56).get(0).getInstrument());
    kl1.keyPressed(new KeyEvent(new JPanel(), 0, 0, 0, KeyEvent.VK_3, '3'));
    ml1.mousePressed(new MouseEvent(new JPanel(), 0, 0, 0, 23, 45, 1, false, MouseEvent.BUTTON1));
    ml1.mouseReleased(new MouseEvent(new JPanel(), 0, 0, 0, 27, 45, 1, false, MouseEvent.BUTTON1));
    assertEquals(4, model1.getNotesPlayingAt(25).get(0).getInstrument());

  }

}
