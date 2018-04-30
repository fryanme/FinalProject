package edu.illinois.cs.cs125.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons = new Button[10][10];

    private boolean Aturn = true;
    private  int round;
    private int Apoint;
    private int Bpoint;
    private TextView tvA;
    private TextView tvB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvA = findViewById(R.id.tv_A);
        tvB = findViewById(R.id.tv_B);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                String bID = "B_" + i + "_" + j;
                int rID = getResources().getIdentifier(bID,"id", getPackageName());
                buttons[i][j] = findViewById(rID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        final Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOver();
            }

        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (Aturn) {
            ((Button) v).setText("x");
        } else {
            ((Button) v).setText("o");
        }
        round++;
        if (win()) {
            if (Aturn) {
                Awin();
            } else {
                Bwin();
            }
        } else if (round == 100) {
            draw();
        } else {
            Aturn = !Aturn;
        }
    }

    private boolean win() {
        String[][] board = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 6; j++) {
                if (!board[i][j].equals("")) {
                    if (board[i][j].equals(board[i][j + 1]) && board[i][j].equals(board[i][j + 2])
                            && board[i][j].equals(board[i][j + 3]) && board[i][j].equals(board[i][j + 4])
                            && !board[i][j].equals("")) {
                        return true;
                    }
                }
            }
        }
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 6; i++) {
                if (!board[i][j].equals("")) {
                    if (board[i][j].equals(board[i + 1][j]) && board[i][j].equals(board[i + 2][j])
                            && board[i][j].equals(board[i + 3][j]) && board[i][j].equals(board[i + 4][j])
                            && !board[i][j].equals("")) {
                        return true;
                    }
                }
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (!board[i][j].equals("")) {
                    if (board[i][j].equals(board[i + 1][j + 1]) && board[i][j].equals(board[i + 2][j + 2])
                            && board[i][j].equals(board[i + 3][j + 3]) && board[i][j].equals(board[i + 4][j + 4])) {
                        return true;
                    }
                }
            }
        }
        for (int i = 9; i >= 4; i--) {
            for (int j = 0; j < 6; j++) {
                if (!board[i][j].equals("")) {
                    if (board[i][j].equals(board[i - 1][j + 1]) && board[i][j].equals(board[i - 2][j + 2])
                            && board[i][j].equals(board[i - 3][j + 3])
                            && board[i][j].equals(board[i - 4][j + 4])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private void Awin() {
        Apoint++;
        Toast.makeText(this, "A wins!", Toast.LENGTH_SHORT).show();
        updatePoints();
        reset();

    }
    private void Bwin() {
        Bpoint++;
        Toast.makeText(this, "B wins!", Toast.LENGTH_SHORT).show();
        updatePoints();
        reset();

    }
    private void draw() {
        Toast.makeText(this,"It's a DRAW",Toast.LENGTH_SHORT).show();
        reset();
    }
    private void updatePoints() {
        tvA.setText("A : " + Apoint);
        tvB.setText("B : " + Bpoint);
    }
    private void reset() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j].setText("");
            }
        }
        round = 0;
        Aturn = true;
    }
    private void startOver() {
        reset();
        Apoint = 0;
        Bpoint = 0;
        updatePoints();

    }
}
