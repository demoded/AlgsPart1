public class Board {
    private int[][] field;
    private int N;
    private final int[][] goalBoard = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    public Board(int[][] blocks) { // construct a board from an N-by-N array of
        field = blocks;            // blocks
        N = field.length;          // (where blocks[i][j] = block in row i,
                                   // column j)
    }

    public int dimension() { // board dimension N
        return N;
    }

    public int hamming() { // number of blocks out of place
        int res = 0;
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) {
                if (field[i][j] != goalBoard[i][j]) res++;
            }
        return res;
    }

    public int manhattan() { // sum of Manhattan distances between blocks and
                             // goal
        return 0;
    }

    public boolean isGoal() { // is this board the goal board?
        return false;
    }

    public Board twin() { // a board obtained by exchanging two adjacent blocks
                          // in the same row
        return this;
    }

    public boolean equals(Object y) { // does this board equal y?
        return false; 
    }

//    public Iterable<Board> neighbors() { // all neighboring boards

//    }

    public String toString() { // string representation of the board (in the
                               // output format specified below)
        return "s";
    }

    public static void main(String[] args) {
        
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) 
            for (int j = 0; j < N; j++) 
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        StdOut.println(initial.dimension());
        StdOut.println(initial.hamming());
    }

}
