package com.example.sujeet.sudoku;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trainee.firstproject.R;

import java.util.Random;

public class Sudoku2 extends AppCompatActivity {
    int px, py, dx, dy;
    static int hscore;
    TextView dummy, score;
    Rect rect[][] = new Rect[11][11];
    Button bu[][] = new Button[11][11];
    int a1[][] = new int[11][11];
    int a2[][] = new int[11][11];
    int a[][]=new int[11][11];
    Random r = new Random();
    Button clr;
    GridLayout grid;
    int ze=0;
    ConstraintLayout constraintLayout;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sudoku2);
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraint);
        grid = (GridLayout) findViewById(R.id.grid);
        final TextView t1, t2, t3, t4, t5, t6, t7, t8, t9,gp;
        View v1,v2,v3,v4;
        // v1=findViewById(R.id.v1);
        //v1.setBackgroundColor(Color.BLACK);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t8 = findViewById(R.id.t8);
        t9 = findViewById(R.id.t9);
        gp=findViewById(R.id.gp);
//

        gp.setText("Generating Puzzle");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) grid.getLayoutParams();
        // GridLayout.LayoutParams params1=(GridLayout.LayoutParams) grid.getLayoutParams();
        int gh,wh;
        // gh=params1.height;
        //  wh=params1.width;
        // v1.setY(gh/3);
        int h = params.height;
        int w = params.width;
        //score=findViewById(R.id.score);
        clr = findViewById(R.id.clr);
        int hx = 0;
        int wx = 0;
        int ij = 1;
        final Handler ha=new Handler();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                bu[i][j] = new Button(Sudoku2.this);
                grid.addView(bu[i][j]);
                GridLayout.LayoutParams par = (GridLayout.LayoutParams) bu[i][j].getLayoutParams();
                bu[i][j].setTextSize(20);
                par.width = w / 9;
                par.height = h / 9;
                bu[i][j].setLayoutParams(par);
                //bu[i][j].setText("" +ij);

            }
        }
        initialize();
        t1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switches(v, event, t1);
                return true;
            }
        });
        t2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switches(v, event, t2);
                return true;
            }
        });
        t3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switches(v, event, t3);
                return true;
            }
        });
        t4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switches(v, event, t4);
                return true;
            }
        });
        t5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switches(v, event, t5);
                return true;
            }
        });
        t6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switches(v, event, t6);
                return true;
            }
        });
        t7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switches(v, event, t7);
                return true;
            }
        });
        t8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switches(v, event, t8);
                return true;
            }
        });
        t9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switches(v, event, t9);
                return true;
            }
        });
        grid.setEnabled(false);
        constraintLayout.setEnabled(false);
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
                gp.setText("Generating Puzzle");
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                ha.postDelayed(new Runnable()
                {
                    @Override
                    public void run() {
                        RandGen4();
                        hide();
                        gp.setText("");
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    }
                },1000);


            }
        });
        for (int i = 0; i < 9; ++i)
            for (int j = 0; j < 9; ++j)
            {
                final int finalI = i;
                final int finalJ = j;
                bu[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bu[finalI][finalJ].setText("");
                    }
                });
            }
        ha.postDelayed(new Runnable() {
            @Override
            public void run() {
                RandGen4();
                 //RandGen5();
                hide();
                gp.setText("");
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        },1000);



    }
    public void switches(View v, MotionEvent event, TextView t) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                px = (int) v.getX();
                py = (int) v.getY();
                dummy = new TextView(Sudoku2.this);
                constraintLayout.addView(dummy);
                dummy.setText(t.getText().toString());
                dummy.setX(v.getX());
                dummy.setY(v.getY()-100);
                dummy.setTextSize(40);
                dx = (int) (v.getX() - event.getRawX());
                dy = (int) (v.getY() - event.getRawY());
                for (int i = 0; i < 9; ++i) {
                    for (int j = 0; j < 9; ++j) {
                        rect[i][j] = new Rect();
                        rect[i][j].left = bu[i][j].getLeft();
                        rect[i][j].right = bu[i][j].getRight();
                        rect[i][j].top = bu[i][j].getTop();
                        rect[i][j].bottom = bu[i][j].getBottom();
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
                dummy.setX(event.getRawX() + dx);
                dummy.setY(event.getRawY() + dy-100);
                break;

            case MotionEvent.ACTION_UP:
                int i;
                for (i = 0; i < 9; ++i) {
                    for (int j = 0; j < 9; ++j) {
                        if (rect[i][j].contains((int) dummy.getX(), (int) dummy.getY())) {
                            if (a[i][j] == 1) {
                                bu[i][j].setText(t.getText().toString());
                                if (CheckRow(i, j)) {
                                    bu[i][j].setTextColor(Color.RED);
                                } else {
                                    bu[i][j].setTextColor(Color.BLACK);
                                }
                                if(CheckifComplete())
                                {
                                    Toast.makeText(Sudoku2.this,"Puzzle solved",Toast.LENGTH_SHORT).show();
                                    clr.setText("Next");
                                }
                                //System.out.println("i:" + i + "j"+j);
                                break;
                            }

                        }
                    }
                }
                constraintLayout.removeView(dummy);
                break;

        }
    }

    public boolean CheckRow(int x, int y) {
        System.out.println("X:" + x + " Y:" + y);
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (bu[x][y].getText().toString().equals(bu[i][j].getText().toString()) && (x == i || y == j)) {
                    if (x != i || y != j)
                        return true;
                }
            }
        }
//        for(int i=0;i<3;i++)
//            for(int j=0;j<3;j++)
//            {
//                if(bu[x][y].getText().toString().equals(bu[i][j].getText().toString()))
//                {
//                    return true;
//                }
//            }
        if (x < 3 && y < 3) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    if (bu[x][y].getText().toString().equals(bu[i][j].getText().toString())) {
                        if (x != i && y != j)
                            return true;
                    }
                }
        } else if (x >= 3 && y < 3 && x < 6) {
            for (int i = 3; i < 6; i++)
                for (int j = 0; j < 3; j++) {
                    if (bu[x][y].getText().toString().equals(bu[i][j].getText().toString())) {
                        if (x != i && y != j)
                            return true;
                    }
                }
        } else if (x >= 6 && y < 3 && x < 9) {
            for (int i = 6; i < 9; i++)
                for (int j = 0; j < 3; j++) {
                    if (bu[x][y].getText().toString().equals(bu[i][j].getText().toString())) {
                        if (x != i && y != j)
                            return true;
                    }
                }
        } else if (x < 3 && y >= 3 && y < 6) {
            for (int i = 0; i < 3; i++)
                for (int j = 3; j < 6; j++) {
                    if (bu[x][y].getText().toString().equals(bu[i][j].getText().toString())) {
                        if (x != i && y != j)
                            return true;
                    }
                }
        } else if (x < 3 && y >= 6 && y < 9) {
            for (int i = 0; i < 3; i++)
                for (int j = 6; j < 9; j++) {
                    if (bu[x][y].getText().toString().equals(bu[i][j].getText().toString())) {
                        if (x != i && y != j)
                            return true;
                    }
                }
        } else if (x >= 3 && y >= 3 && x < 6 && y < 6) {
            for (int i = 3; i < 6; i++)
                for (int j = 3; j < 6; j++) {
                    if (bu[x][y].getText().toString().equals(bu[i][j].getText().toString())) {
                        if (x != i && y != j)
                            return true;
                    }
                }
        } else if (x >= 3 && y >= 6 && x < 6 && y < 9) {
            for (int i = 3; i < 6; i++)
                for (int j = 6; j < 9; j++) {
                    if (bu[x][y].getText().toString().equals(bu[i][j].getText().toString())) {
                        if (x != i && y != j)
                            return true;
                    }
                }
        }
        if (x >= 6 && x < 9 && y >= 3 && y < 6) {
            for (int i = 6; i < 9; i++)
                for (int j = 3; j < 6; j++) {
                    if (bu[x][y].getText().toString().equals(bu[i][j].getText().toString())) {
                        if (x != i && y != j)
                            return true;
                    }
                }
        }
        if (x >= 6 && x < 9 && y >= 6 && y < 9) {
            for (int i = 6; i < 9; i++)
                for (int j = 6; j < 9; j++) {
                    if (bu[x][y].getText().toString().equals(bu[i][j].getText().toString())) {
                        if (x != i && y != j)
                            return true;
                    }
                }
        }
        return false;
    }

    public boolean CheckifComplete() {
        for (int i = 0; i < 9; ++i)
            for (int j = 0; j < 9; ++j) {
                ColorStateList mlist = bu[i][j].getTextColors();
                int c = mlist.getDefaultColor();
                if (bu[i][j].getText().toString().equals("") || c == Color.RED)
                    return false;
            }
        return true;
    }
    public void RandGen4() {
        int x;
        for(int i=0;i<9;i++)
            for(int j=0;j<9;++j)
            {
                bu[i][j].setText("");
            }

            for (int i = 0; i < 9; i++)
                for (int j = 0; j < 9; ++j) {
                    x = r.nextInt(9) + 1;
                    if ((i < 3 && j < 3) || (i < 6 && j < 6 && i >= 3 && j >= 3) || (i >= 6 && j >= 6)) {
                        bu[i][j].setText("" + x);
                        if (!CheckRow(i, j)) {
                            bu[i][j].setTextColor(Color.BLACK);
                            bu[i][j].setEnabled(false);
                        } else {
                            bu[i][j].setText("");
                            j = j - 1;
                        }


                    }
                }

        fillRemaining(0,3);

       if(!CheckifComplete())
            RandGen4();


    }
    public boolean fillRemaining(int i, int j)
    {
        if (j>=9 && i<9-1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>=9&& j>=9)
            return true;

        if (i < 3)
        {
            if (j < 3)
                j = 3;
        }
        else if (i < 6)
        {
            if (j==(int)(i/3)*3)
                j =  j + 3;
        }
        else
        {
            if (j == 6)
            {
                i = i + 1;
                j = 0;
                if (i>=9)
                    return true;
            }
        }

        for (int num = 1; num<=9; num++)
        {
            bu[i][j].setText(""+num);
            if (!CheckRow(i,j))
            {
                bu[i][j].setTextColor(Color.BLACK);
                bu[i][j].setEnabled(false);
                if (fillRemaining(i, j+1))
                    return true;

                bu[i][j].setText("");
                bu[i][j].setEnabled(true);
            }
            //bu[i][j].setText("");
        }
        return false;
    }
    public boolean CheckRowWithNum(int x, int y,int num) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j)
                if (bu[x][y].getText().toString().equals(bu[i][j].getText().toString()) && (x == i || y == j))
                {
                    if (x != i || y != j)
                        return true;
                }
        }
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
            {
                if(bu[x][y].getText().toString().equals(bu[i][j].getText().toString()))
                {
                    return true;
                }
            }
        if (x < 3 && y < 3) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    if(bu[x][y].getText().toString().equals(bu[i][j].getText().toString()))                    {
                        if (x != i && y != j)
                            return true;
                    }
                }
        } else if (x >= 3 && y < 3 && x < 6) {
            for (int i = 3; i < 6; i++)
                for (int j = 0; j < 3; j++) {
                    if(bu[x][y].getText().toString().equals(bu[i][j].getText().toString()))                    {
                        if (x != i && y != j)
                            return true;
                    }
                }
        } else if (x >= 6 && y < 3 && x < 9) {
            for (int i = 6; i < 9; i++)
                for (int j = 0; j < 3; j++) {
                    if(bu[x][y].getText().toString().equals(bu[i][j].getText().toString()))                    {
                        if (x != i && y != j)
                            return true;
                    }
                }
        } else if (x < 3 && y >= 3 && y < 6) {
            for (int i = 0; i < 3; i++)
                for (int j = 3; j < 6; j++) {
                    if(bu[x][y].getText().toString().equals(bu[i][j].getText().toString()))                    {
                        if (x != i && y != j)
                            return true;
                    }
                }
        } else if (x < 3 && y >= 6 && y < 9) {
            for (int i = 0; i < 3; i++)
                for (int j = 6; j < 9; j++) {
                    if(bu[x][y].getText().toString().equals(bu[i][j].getText().toString()))                    {
                        if (x != i && y != j)
                            return true;
                    }
                }
        } else if (x >= 3 && y >= 3 && x < 6 && y < 6) {
            for (int i = 3; i < 6; i++)
                for (int j = 3; j < 6; j++) {
                    if(bu[x][y].getText().toString().equals(bu[i][j].getText().toString()))                    {
                        if (x != i && y != j)
                            return true;
                    }
                }
        } else if (x >= 3 && y >= 6 && x < 6 && y < 9) {
            for (int i = 3; i < 6; i++)
                for (int j = 6; j < 9; j++) {
                    if(bu[x][y].getText().toString().equals(bu[i][j].getText().toString()))                    {
                        if (x != i && y != j)
                            return true;
                    }
                }
        }
        if (x >= 6 && x < 9 && y >= 3 && y < 6) {
            for (int i = 6; i < 9; i++)
                for (int j = 3; j < 6; j++) {
                    if(bu[x][y].getText().toString().equals(bu[i][j].getText().toString()))                    {
                        if (x != i && y != j)
                            return true;
                    }
                }
        }
        if (x >= 6 && x < 9 && y >= 6 && y < 9) {
            for (int i = 6; i < 9; i++)
                for (int j = 6; j < 9; j++) {
                    if(bu[x][y].getText().toString().equals(bu[i][j].getText().toString()))                    {
                        if (x != i && y != j)
                            return true;
                    }
                }
        }
        return false;
    }
    public void hide()
    {
        int x,y;
        for(int i=0;i<30;i++)
        {
            x=r.nextInt(9);
            y=r.nextInt(9);
            if(a[x][y]==0)
            {
                bu[x][y].setText("");
                a[x][y]=1;
                bu[x][y].setEnabled(true);
            }
            else
                i=i-1;
        }
        clr.setText("Change");
    }
    public void initialize()
    {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; ++j) {
                a1[i][j] = 0;
                a2[i][j] = 0;
                a[i][j]=0;
            }
        }
    }
    public void RandGen5()
    {
        int x,y,z;
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
            {
                if(i==j) {
                    for (int k = i; k < 9; ++k) {
                        z = r.nextInt(9) + 1;
                        bu[i][k].setText("" + z);
                        if (!CheckRow(i, k)) {
                            bu[i][k].setTextColor(Color.BLACK);
                            bu[i][k].setEnabled(false);
                        } else {
                            bu[i][k].setText("");
                            k=i;
                        }
                    }
                    for (int k = j; k < 9; ++k) {
                        z = r.nextInt(9) + 1;
                        bu[k][j].setText("" + z);
                        if (!CheckRow(k, j)) {
                            bu[k][j].setTextColor(Color.BLACK);
                            bu[k][j].setEnabled(false);
                        } else {
                            bu[k][j].setText("");
                            k=j;
                        }
                    }

                }
            }
    }
}
