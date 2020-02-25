package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    @FXML
    Rectangle rect1, rect2, rect3, rect4;
    Timer timer;
    int delay = 0, period = 2;
    boolean stage1 = false;
    boolean stage2 = false;
    boolean stage3 = false;
    boolean stage4 = false;

    @FXML
    public void onStart() {
        if (timer == null) {
            timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        if (stage1) {
                            if (stage2) {
                                if (stage3) {
                                    if (stage4) {
                                        stage1 = false;
                                        stage2 = false;
                                        stage3 = false;
                                        stage4 = false;
                                    } else {
                                        if (rect1.getLayoutY() == 336) {
                                            stage4 = true;
                                        } else {
                                            move(rect1, 0, 1);
                                        }
                                    }
                                } else {
                                    if (rectangleEquals(rect1, rect4)) {
                                        stage3 = true;
                                    } else {
                                        move(rect1, -1, 0);
                                    }
                                }
                            } else {
                                if (rectangleEquals(rect1, rect3)) {
                                    stage2 = true;
                                } else {
                                    move(rect1, 0, -1);
                                }
                            }
                        } else {
                            if (rectangleEquals(rect1, rect2)) {
                                stage1 = true;
                            } else {
                                move(rect1, 1, 0);
                            }
                        }
                    });
                }
            }, delay, period);
        }
    }

    private void move(Rectangle rect, int x, int y) {
        if (y != 0)
            rect.setLayoutY(rect.getLayoutY() + y);
        if (x != 0)
            rect.setLayoutX(rect.getLayoutX() + x);
    }

    private boolean rectangleEquals(Rectangle r1, Rectangle r2) {
        return r1.getLayoutX() == r2.getLayoutX() && r1.getLayoutY() == r2.getLayoutY();
    }

    public void stopTimer() {
        if (timer == null) return;
        timer.cancel();
    }
}
