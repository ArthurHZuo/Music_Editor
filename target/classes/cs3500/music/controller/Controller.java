package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import cs3500.music.model.MusicEditorControllerModel;
import cs3500.music.model.NoteBuilderImp;
import cs3500.music.model.NoteModel;
import cs3500.music.otherView.OtherCompositeView;

/**
 * Controller for GuiView type views.
 */
public class Controller implements MusicEditorController {
  private final OtherCompositeView view;
  private final MusicEditorControllerModel model;
  private final Timer timer;
  private int startNote;
  private boolean shiftPress;
  private int instNum;
  private NoteModel changeNote;


  public Controller(OtherCompositeView
                        view, MusicEditorControllerModel model) {
    this.view = view;
    this.model = model;
    this.timer = new Timer();
    this.startNote = -1;
    this.shiftPress = false;
    this.instNum = 0;
    this.changeNote = null;
    setKeyHandler();
    setMouseHandler();

  }

  @Override
  public void run() throws IOException {
    this.view.render();
    this.timer.scheduleAtFixedRate(
        new ModelTimer(this.model, this.view), 100, this.model.getTempo() / 1000);
  }

  private void setKeyHandler() {
    HashMap<Integer, Runnable> onPress = new HashMap<>();
    HashMap<Integer, Runnable> onRelease = new HashMap<>();
    HashMap<Integer, Runnable> onTyped = new HashMap<>();
    onPress.put(82,
        () -> {
          for (int i = 0; i < model.lastBeat(); i++) {
            for (NoteModel note : model.getNotesStartingAt(i)) {
              model.removeNote(note);
            }
          }
          view.repaint();
        });

    onPress.put(16,
        () -> {
          this.shiftPress = true;
        });
    onRelease.put(16,
        () -> {
          this.shiftPress = false;
        });
    onPress.put(48,
        () -> {
          this.instNum = 0;
        });
    onRelease.put(48,
        () -> {
          if (this.instNum == 0) {
            this.shiftPress = false;
          }
        });
    onPress.put(49,
        () -> {
          this.instNum = 1;
        });
    onRelease.put(49,
        () -> {
          if (this.instNum == 1) {
            this.shiftPress = false;
            this.instNum = 0;
          }
        });
    onPress.put(50,
        () -> {
          this.instNum = 2;
        });
    onRelease.put(50,
        () -> {
          if (this.instNum == 2) {
            this.shiftPress = false;
            this.instNum = 0;
          }
        });
    onPress.put(51,
        () -> {
          this.instNum = 3;
        });
    onRelease.put(51,
        () -> {
          if (this.instNum == 3) {
            this.shiftPress = false;
            this.instNum = 0;
          }
        });
    onPress.put(52,
        () -> {
          this.instNum = 4;
        });
    onRelease.put(52,
        () -> {
          if (this.instNum == 4) {
            this.shiftPress = false;
            this.instNum = 0;
          }
        });
    onPress.put(53,
        () -> {
          this.instNum = 5;
        });
    onRelease.put(53,
        () -> {
          if (this.instNum == 5) {
            this.shiftPress = false;
            this.instNum = 0;
          }
        });
    onPress.put(54,
        () -> {
          this.instNum = 6;
        });
    onRelease.put(54,
        () -> {
          if (this.instNum == 6) {
            this.shiftPress = false;
            this.instNum = 0;
          }
        });
    onPress.put(55,
        () -> {
          this.instNum = 7;
        });
    onRelease.put(55,
        () -> {
          if (this.instNum == 7) {
            this.shiftPress = false;
            this.instNum = 0;
          }
        });
    onPress.put(56,
        () -> {
          this.instNum = 8;
        });
    onRelease.put(56,
        () -> {
          if (this.instNum == 8) {
            this.shiftPress = false;
            this.instNum = 0;
          }
        });
    onPress.put(57,
        () -> {
          this.instNum = 9;
        });
    onRelease.put(57,
        () -> {
          if (this.instNum == 9) {
            this.shiftPress = false;
            this.instNum = 0;
          }
        });
    onPress.put(32,
        () -> {
          this.view.startMidi();
          this.model.togglePlaying();
        });
    onPress.put(38,
        () -> {
          //this.view.reactToDirectionalKey(true);
        });
    onPress.put(37,
        () -> {
          //this.view.tickBeat();

        });
    onPress.put(39,
        () -> {
          //view.model);
        });
    onPress.put(36,
        () -> {
          this.view.toBeginning();
          view.repaint();
        });
    onPress.put(35,
        () -> {
          view.toEnd();
          view.repaint();
        });
    onPress.put(40,
        () -> {
          //this.view.reactToDirectionalKey(false);
        });
    view.addKeyListener(
        new KeyboardHandler(
            onPress,
            onRelease,
            onTyped));
  }

  private void setMouseHandler() {
    HashMap<Integer, Consumer<MouseEvent>> onClick = new HashMap<>();
    HashMap<Integer, Consumer<MouseEvent>> onPress = new HashMap<>();
    HashMap<Integer, Consumer<MouseEvent>> onRelease = new HashMap<>();
    onPress.put(MouseEvent.BUTTON1,
        (MouseEvent me) -> {
          if (shiftPress) {
            for (NoteModel note : this.model.getNotesPlayingAt(view.getClickedBeat(me.getX()))) {
              if (note.getPitch().getMidiPitch(note.getOctave())
                  == view.getClickedPitch(me.getY())) {
                this.model.removeNote(note);
              }
            }
          } else {
            NoteModel tempNote = null;
            for (NoteModel note : this.model.getNotesPlayingAt(view.getClickedBeat(me.getX()))) {
              if (note.getPitch().getMidiPitch(note.getOctave())
                  == view.getClickedPitch(me.getY())) {
                tempNote = note;
              }
            }
            if (tempNote == null) {
              this.startNote = view.getClickedBeat(me.getX());
            } else {
              this.changeNote = tempNote;
            }
          }
          view.repaint();

        });
    onRelease.put(MouseEvent.BUTTON1,
        (MouseEvent me) -> {
          if (!this.shiftPress) {
            if (this.changeNote != null) {
              this.model.editNote(this.changeNote, new NoteBuilderImp()
                  .setEnd(this.view.getClickedBeat(me.getX()) + this.changeNote.getDuration())
                  .setStart(this.view.getClickedBeat(me.getX()))
                  .setInstrument(this.changeNote.getInstrument())
                  .setPitch(this.view.getClickedPitch(me.getY()))
                  .setVolume(this.changeNote.getVolume())
                  .build());
              this.changeNote = null;
            } else {
              if (this.startNote != -1 && this.startNote < this.view.getClickedBeat(me.getX())) {
                this.model.addNote(new NoteBuilderImp()
                    .setStart(this.startNote)
                    .setInstrument(this.instNum + 1)
                    .setEnd(this.view.getClickedBeat(me.getX()))
                    .setPitch(this.view.getClickedPitch(me.getY())).build());
              }
            }
          }
          view.repaint();
        });
    view.addMouseListener(
        new MouseHandler(
            onClick,
            onPress,
            onRelease));
  }

}

class ModelTimer extends TimerTask {

  private final MusicEditorControllerModel model;
  private final OtherCompositeView view;

  ModelTimer(MusicEditorControllerModel model, OtherCompositeView view) {
    this.view = view;
    this.model = model;
  }

  @Override
  public void run() {
    if (model.isPlaying()) {
      if (this.model.getCurrentBeat() <= this.model.lastBeat() + 1) {
        this.view.tickBeat();
        this.view.startMidi();
      } else {
        this.view.toBeginning();
      }
      this.view.render();
    }
  }
}