package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.MusicEditorViewModel;

/**
 * Created by Spencer on 11/19/15. A class representing a CompositeView, which puts together a
 * MidiView and a GuiView to result in a composite view that scrolls the music along as the song is
 * playing.
 */
public class CompositeView implements MusicEditorGuiView {
  GuiViewFrame guiView;
  MidiView midiView;

  /**
   * Constructs a new {@link CompositeView} object, which contains a GUI view to render and a Midi
   * view to play.
   *
   * @param guiView  the graphical view to render as part of the composite view.
   * @param midiView the midi view to play as part of the composite view.
   */
  public CompositeView(GuiViewFrame guiView, MidiViewImpl midiView) {
    this.guiView = guiView;
    this.midiView = midiView;
  }

  @Override
  public void view(MusicEditorViewModel vm) throws IOException {
    this.guiView.setVisible(true);
    this.guiView.repaint();
    if (vm.isPlaying()) {
      try {
        this.midiView.play(vm, vm.getCurrentBeat());
      } catch (InvalidMidiDataException imde) {
        System.out.println("Invalid Midi Data: " + imde.getMessage());
      }
    }
  }

  @Override
  public void reactToDirectionalKey(boolean moveUp) {
    this.guiView.reactToDirectionalKey(moveUp);
  }

  @Override
  public void reactCompletelyToDirectionalKey(boolean moveUp) {
    this.guiView.reactCompletelyToDirectionalKey(moveUp);
  }

  @Override
  public void addKeyListener(KeyListener kl) {
    this.guiView.addKeyListener(kl);
  }

  @Override
  public void addMouseListener(MouseListener ml) {
    this.guiView.addMouseListener(ml);
  }

  @Override
  public int getClickedPitch(int yCoord) {
    return this.guiView.getClickedPitch(yCoord);
  }

  @Override
  public int getClickedBeat(int xCoord) {
    return this.guiView.getClickedBeat(xCoord);
  }

}
