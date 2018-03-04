package com.example.jonas.sudokusolverforandroid;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import solver.SudokuSolver;
/**
 * Created by jonas on 2018-03-02.
 */

public class NumberAdapter extends BaseAdapter {

    private Context context;
    private EditText[][] myBoxes = new EditText[9][9];

    public NumberAdapter(Context context) {

        this.context = context;

        for(int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                myBoxes[row][col] = new EditText(context);
                myBoxes[row][col].setText("");
                myBoxes[row][col].setFilters(new InputFilter[] {new InputFilter.LengthFilter(1)});
                myBoxes[row][col].setInputType(InputType.TYPE_CLASS_NUMBER);

            }
        }


    }

    @Override
    public int getCount() {
        return 81;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return myBoxes[i/9][i%9];
    }

    public boolean solveIt() {

        System.out.println("WOOWWWW");
        SudokuSolver newSolve = new SudokuSolver(9, 9);
        System.out.println("ajiodjsioajd");
        for(int row = 0; row < 9; row++) {
            for(int col = 0; col < 9; col++) {
                if(myBoxes[row][col].getText().toString().isEmpty()) newSolve.sudokuMatrix[row][col] = 0;
                else {
                    newSolve.sudokuMatrix[row][col] = Integer.parseInt(myBoxes[row][col].getText().toString());
                }


            }
        }

        newSolve.solve();
        System.out.println("1");
        for(int x = 0; x < 9; x++) {
            System.out.println("2");
            for (int y = 0; y < 9; y++) {
                System.out.println("3");
                myBoxes[x][y].setText(String.valueOf(newSolve.sudokuMatrix[x][y]));
            }
        }
        return false;

    }
    public void clearIt(){

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                myBoxes[i][j].setText("");
            }
        }
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}