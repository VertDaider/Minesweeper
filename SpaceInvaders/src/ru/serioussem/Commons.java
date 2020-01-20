package ru.serioussem;

public interface Commons {
    int BOARD_WIDTH = 1000;
    int BOARD_HEIGHT = 750;

    int BORDER_RIGHT = 30;
    int BORDER_LEFT = 5;

    int GROUND = BOARD_HEIGHT - 50;
    int BOMB_HEIGHT = 5;

    int ALIEN_HEIGHT = 18;
    int ALIEN_WIDTH = 18;
    int ALIEN_INIT_X = 150;
    int ALIEN_INIT_Y = 5;
    int ALIEN_ROW = 5;
    int ALIEN_COLUMN = 10;

    int GO_DOWN = 15;
    int NUMBER_OF_ALIENS_TO_DESTROY = ALIEN_ROW * ALIEN_COLUMN;
    int CHANCE = 5;
    int DELAY = 17;
    int PLAYER_WIDTH = 15;
    int PLAYER_HEIGHT = 10;
}
