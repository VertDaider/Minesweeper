package sweeper;

class Flag {
    private Matrix flagMap;
    private int countOfCloseBoxes;
    private int countOfFlagedBoxes;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfCloseBoxes = Ranges.getSize().x * Ranges.getSize().y;
        countOfFlagedBoxes = 0;
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfCloseBoxes--;
    }

    void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGED:
                setClosedToBox(coord);
                countOfFlagedBoxes--;
                break;
            case CLOSED:
                setFlagedToBox(coord);
                countOfFlagedBoxes++;
                break;
        }
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    private void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }

    void setFlagToClosedBombBox() {
        for (Coord around : Ranges.getAllCoords())
            if (flagMap.get(around) == Box.CLOSED)
            flagMap.set(around, Box.FLAGED);
    }

    int getCountOfClosedBoxes() {
        return countOfCloseBoxes;
    }

    int getCountOfFlagedBoxes() {
        return countOfFlagedBoxes;
    }

    void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenedToClosedBombBox(Coord coord) {
        if (flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord, Box.OPENED);
    }

    void setNobombToFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGED)
            flagMap.set(coord, Box.NOBOMB);
    }

    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord))
            if (flagMap.get(around) == Box.FLAGED)
                count++;
        return count;
    }
}
