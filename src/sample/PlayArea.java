package sample;

public class PlayArea {
    // This class handles everything about the playField. Methods like check if someone won, print the play field and placing markers.

    private String[] playField = {" ", " ", " ", " ", " ", " ", " ", " ", " "};;

    public boolean placeMarker(String marker, int valueInput) {
        // this method places a player marker in the playField and returns the playField with the marker position

                if (valueInput > 0 && valueInput < 10) {
                    if (playField[valueInput - 1] != "X" && playField[valueInput - 1] != "O") {
                        playField[valueInput - 1] = marker;
                        return true;

                    } else {
                    }
        }
                return false;
    }

    public boolean isWinner(String marker) {
        // This method checks if a player won and returns true or false.

        if (((playField[0] == playField[1]) && (playField[1] == playField[2]) && (playField[2] == marker)) ||
                ((playField[3] == playField[4]) && (playField[4] == playField[5]) && (playField[5] == marker)) ||
                ((playField[6] == playField[7]) && (playField[7] == playField[8]) && (playField[8] == marker)) ||
                ((playField[0] == playField[3]) && (playField[3] == playField[6]) && (playField[6] == marker)) ||
                ((playField[1] == playField[4]) && (playField[4] == playField[7]) && (playField[7] == marker)) ||
                ((playField[2] == playField[5]) && (playField[5] == playField[8]) && (playField[8] == marker)) ||
                ((playField[0] == playField[4]) && (playField[4] == playField[8]) && (playField[8] == marker)) ||
                ((playField[2] == playField[4]) && (playField[4] == playField[6]) && (playField[6] == marker))) {
            return true;

        } else {
            return false;
        }
    }

    public void resetPlayArea() {
        String[] playField = {" ", " ", " ", " ", " ", " ", " ", " ", " "};;
    }

    public String[] getPlayField() {
        return playField;
    }
}
