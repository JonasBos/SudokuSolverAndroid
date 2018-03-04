package solver;

public class Index {
    private int row, col;

    public Index(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow(){ return row; }

    public int getCol(){ return col; }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Index) {
            Index p = (Index) obj;
            return row == p.row && col == p.col;
        } else {
            return false;
        }
    }
}
