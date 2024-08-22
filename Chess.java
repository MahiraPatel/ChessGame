import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Chess{
public static LinkedList<Piece> ps = new LinkedList<>();
    public static Piece selectedPiece = null;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{        

        
        BufferedImage all = ImageIO.read(new File("D:\\Java_Course\\Code_Practice\\ChessGame\\chess.png"));
        Image imgs[] = new Image[12];
        int ind = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
             ind++;
    }
}

        // Black pieces
        Piece bking = new King(4, 0, false, ps); // Black king
        Piece bqueen = new Queen(3, 0, false, ps); // Black queen
        Piece brook1 = new Rook(0, 0, false, ps); // Black rook 1
        Piece brook2 = new Rook(7, 0, false, ps); // Black rook 2
        Piece bknight1 = new Knight(1, 0, false, ps); // Black knight 1
        Piece bknight2 = new Knight(6, 0, false, ps); // Black knight 2
        Piece bbishop1 = new Bishop(2, 0, false, ps); // Black bishop 1
        Piece bbishop2 = new Bishop(5, 0, false, ps); // Black bishop 2
        Piece bpawn1 = new Pawn(0, 1, false, ps); // Black pawn 1
        Piece bpawn2 = new Pawn(1, 1, false, ps); // Black pawn 2
        Piece bpawn3 = new Pawn(2, 1, false, ps); // Black pawn 3
        Piece bpawn4 = new Pawn(3, 1, false, ps); // Black pawn 4
        Piece bpawn5 = new Pawn(4, 1, false, ps); // Black pawn 5
        Piece bpawn6 = new Pawn(5, 1, false, ps); // Black pawn 6
        Piece bpawn7 = new Pawn(6, 1, false, ps); // Black pawn 7
        Piece bpawn8 = new Pawn(7, 1, false, ps); // Black pawn 8

        // White pieces
        Piece wking = new King(4, 7, true, ps); // White king
        Piece wqueen = new Queen(3, 7, true, ps); // White queen
        Piece wrook1 = new Rook(0, 7, true, ps); // White rook 1
        Piece wrook2 = new Rook(7, 7, true, ps); // White rook 2
        Piece wknight1 = new Knight(1, 7, true, ps); // White knight 1
        Piece wknight2 = new Knight(6, 7, true, ps); // White knight 2
        Piece wbishop1 = new Bishop(2, 7, true, ps); // White bishop 1
        Piece wbishop2 = new Bishop(5, 7, true, ps); // White bishop 2
        Piece wpawn1 = new Pawn(0, 6, true, ps); // White pawn 1
        Piece wpawn2 = new Pawn(1, 6, true, ps); // White pawn 2
        Piece wpawn3 = new Pawn(2, 6, true, ps); // White pawn 3
        Piece wpawn4 = new Pawn(3, 6, true, ps); // White pawn 4
        Piece wpawn5 = new Pawn(4, 6, true, ps); // White pawn 5
        Piece wpawn6 = new Pawn(5, 6, true, ps); // White pawn 6
        Piece wpawn7 = new Pawn(6, 6, true, ps); // White pawn 7
        Piece wpawn8 = new Pawn(7, 6, true, ps); // White pawn 8
        
        JFrame frame = new JFrame();
        frame.setBounds(10,10,512,512);
        frame.setUndecorated(true);
        JPanel pn = new JPanel(){
            @Override
             public void paint(Graphics g) {
            boolean white=true;
                for(int y= 0;y<8;y++){
            for(int x= 0;x<8;x++){
                if(white){
                    g.setColor(new Color(235,235, 208));
                }else{
                    g.setColor(new Color(119, 148, 85));
                    
                }
                g.fillRect(x*64, y*64, 64, 64);
            white=!white;
            }
            white=!white;
            }
                for(Piece p: ps){
                    int ind=0;
                    if(p.name.equalsIgnoreCase("king")){
                        ind=0;
                    }
                    if(p.name.equalsIgnoreCase("queen")){
                        ind=1;
                    }
                    if(p.name.equalsIgnoreCase("bishop")){
                        ind=2;
                    }
                    if(p.name.equalsIgnoreCase("knight")){
                        ind=3;
                    }
                    if(p.name.equalsIgnoreCase("rook")){
                        ind=4;
                    }
                    if(p.name.equalsIgnoreCase("pawn")){
                        ind=5;
                    }
                    if(!p.isWhite){
                        ind+=6;
                    }
                    g.drawImage(imgs[ind], p.x, p.y, this);
                }
            
            }
        };
        frame.add(pn);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        frame.addMouseMotionListener(new MouseMotionListener (){
            @Override
            public void mouseDragged(MouseEvent e) {
                if(selectedPiece!=null){
                    selectedPiece.x=e.getX();
                    selectedPiece.y=e.getY();
                    frame.repaint();
                }
                
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        
        });
        
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //System.out.println((getPiece(e.getX(), e.getY()).isWhite?"White ":"black ")+getPiece(e.getX(), e.getY()).name);
                //new //selectedPiece = getPiece(e.getX(),e.getY());
               
                selectedPiece = getPiece(e.getX(),e.getY());
                if(selectedPiece != null) {
                    // Store the initial position of the piece
                    selectedPiece.initialXp = selectedPiece.xp;
                    selectedPiece.initialYp = selectedPiece.yp;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // selectedPiece.move(e.getX()/64, e.getY()/64);
                // frame.repaint();
                if(selectedPiece != null) {
                    selectedPiece.move(e.getX()/64, e.getY()/64);
                    frame.repaint();
                    selectedPiece = null; // Reset the selected piece
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
            
        });
        

        
    }
    
    
    public static Piece getPiece(int x, int y){
        int xp = x/64;
        int yp = y/64;
        for(Piece p: ps){
            if(p.xp == xp && p.yp == yp){
                return p;
            }
        }
        return null;
    }

    /**
     * @return
     */
    public static List<Piece> getPieces() {
        return ps;
    }

    public static boolean getTurn() {
        // You need to implement the logic to determine whose turn it is
        // For now, let's assume it's always white's turn
        return true;
    }

    public static boolean makeMove(Piece piece, Move move) {
        // You need to implement the logic to make a move
        // For now, let's assume the move is always valid
        piece.move(move.getX(), move.getY());
        return true;
    }
    
    public static void undoMove() {
        // You need to implement the logic to undo a move
        // For now, let's assume the move is always undone
    }

    private boolean isCheckmate() {
        // Check if the king is in check
        if (!isKingInCheck()) {
            return false;
        }
    
        // Check if there are any possible moves that can get the king out of check
        for (Piece piece : getPieces()) {
            if (piece.getColor() == getTurn()) {
                List<Move> possibleMoves = piece.getPossibleMoves();
                for (Move move : possibleMoves) {
                    // Make a temporary move
                    piece.move(move.getX(), move.getY());
        
                    // Check if the king is still in check
                    if (!isKingInCheck()) {
                        // Undo the temporary move
                        piece.move(piece.getXP(), piece.getYP());
                        return false;
                    }
        
                    // Undo the temporary move
                    piece.move(piece.getXP(), piece.getYP());
                }
            }
        }
        // If no moves can get the king out of check, it's checkmate
        return true;
    }

    private boolean isStalemate() {
        // Check if the king is not in check
        if (isKingInCheck()) {
            return false;
        }

        // Check if there are any possible moves
        for (Piece piece : getPieces()) {
            if (piece.getColor() == getTurn()) {
                for (Move move : piece.getPossibleMoves()) {
                    if (makeMove(piece, move)) {
                        undoMove();
                        return false;
                    }
                }
            }
        }

        // If no moves are possible, it's stalemate
        return true;
    }

    private boolean isKingInCheck() {
        // Check if the king is under attack by an opponent's piece
        for (Piece piece : getPieces()) {
            if (piece.getColor() != getTurn()) {
                for (Move move : piece.getPossibleMoves()) {
                    if (move.getTargetSquare().equals(getKingSquare())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static Square getKingSquare() {
        // You need to implement the logic to get the king's square
        // For now, let's assume the king is always at (4, 0) for black and (4, 7) for white
        if (getTurn()) {
            return new Square(4, 7);
        } else {
            return new Square(4, 0);
        }
    }

    

    
}


class Move {
    private int x;
    private int y;
    private Square targetSquare;

    public Move(int x, int y, Square targetSquare) {
        this.x = x;
        this.y = y;
        this.targetSquare = targetSquare;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Square getTargetSquare() {
        return targetSquare;
    }
}

class Square {
    private int x;
    private int y;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

    abstract class Piece {
        int xp, yp;
        int x, y; 
        boolean isWhite;
        LinkedList<Piece> ps;
        String name;
        int initialXp, initialYp; //new added

        public int getXP() {
            return xp;
        }
    
        public int getYP() {
            return yp;
        }
    
        public boolean getColor() {
            return isWhite;
        }
    
        public LinkedList<Piece> getPieces() {
            return ps;
        }
    
        public abstract List<Move> getPossibleMoves();

        public abstract boolean isValidMove(int newX, int newY);
        
        public  boolean isUnderAttack(){
            for (Piece p : ps) {
                if (p.isWhite != isWhite && p.isValidMove(xp, yp)) {
                    return true;
                }
            }
            return false;
        }

        public abstract boolean canCastle();
        
        public boolean hasMoved() {
            return xp != initialXp || yp != initialYp;
        }

        public Piece(int xp, int yp, boolean isWhite, String n, LinkedList<Piece> ps){
        this.xp = xp;
        this.yp = yp;
        x= xp*64;
        y= yp*64;
        this.isWhite = isWhite;
        this.ps = ps;
        name = n;
         ps.add(this);
        }
        
        public void move(int xp, int yp){
    //        ps.stream().filter((p) -> (p.xp==xp&&p.yp==yp)).forEachOrdered((p) -> {
    //            p.kill();
    //        });
            if(Chess.getPiece(xp*64, yp*64)!= null){
                if(Chess.getPiece(xp*64, yp*64).isWhite!=isWhite){
                    Chess.getPiece(xp*64, yp*64).kill();
                }else{
                    // x= this.xp*64;
                    // y= this.yp*64;
                    // return;
                    x= this.initialXp*64;
                    y= this.initialYp*64;
                    this.xp = initialXp;
                    this.yp = initialYp;
                    return;
                }
                
                
            }
            
            this.xp=xp;
            this.yp = yp;
            x= xp*64;
            y= yp*64;
        }

         
        public void kill(){
            ps.remove(this);
        }
    }
    


  
    class King extends Piece {
        King(int xp, int yp, boolean isWhite, LinkedList<Piece> ps) {
            super(xp, yp, isWhite, "king", ps);
        }
    
        @Override
        public List<Move> getPossibleMoves() {
            List<Move> moves = new ArrayList<>();
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) continue; // don't move to the same square
                    int newX = xp + dx;
                    int newY = yp + dy;
                    if (isValidMove(newX, newY)) {
                        moves.add(new Move(newX, newY, new Square(newX, newY)));
                    }
                }
            }
            return moves;
        }
    
        @Override
        public boolean isValidMove(int newX, int newY) {
            // King can move one square in any direction (horizontally, vertically, or diagonally)
            int dx = Math.abs(newX - xp);
            int dy = Math.abs(newY - yp);
            return (dx == 1 && dy == 0) || (dx == 0 && dy == 1) || (dx == 1 && dy == 1);
        }
    
        @Override
        public boolean isUnderAttack() {
            // Check if any opponent piece can capture the king
            for (Piece p : ps) {
                if (p.isWhite != isWhite && p.isValidMove(xp, yp)) {
                    return true;
                }
            }
            return false;
        }
    
        
        public boolean canCastle() {
            // King can castle if it has not moved, and there are no pieces between the king and the rook
            if (hasMoved()) return false;
            if (isUnderAttack()) return false;
            Piece rook = getRook();
            if (rook == null) return false;
            if (rook.hasMoved()) return false;
            int dx = rook.xp - xp;
            for (int i = 1; i < Math.abs(dx); i++) {
                int x = xp + i * (dx / Math.abs(dx));
                if (isSquareOccupied(x, yp)) return false;
            }
            return true;
        }
    
        private Piece getRook() {
            // Find the rook on the same rank as the king
            for (Piece p : ps) {
                if (p instanceof Rook && p.isWhite == isWhite && p.yp == yp) {
                    return p;
                }
            }
            return null;
        }
    
        @Override
        public boolean hasMoved() {
            return xp != initialXp || yp != initialYp;
        }
    
        private boolean isSquareOccupied(int x, int y) {
            // Check if a square is occupied by a piece
            for (Piece p : ps) {
                if (p.xp == x && p.yp == y) {
                    return true;
                }
            }
            return false;
        }
    }

    
    class Queen extends Piece {

        Queen(int xp, int yp, boolean isWhite, LinkedList<Piece> ps) {
            super(xp, yp, isWhite, "queen", ps);
        }

        @Override
        public List<Move> getPossibleMoves() {
            List<Move> moves = new ArrayList<>();

            // Horizontal moves
            for (int dx = -1; dx <= 1; dx++) {
                int newX = xp + dx;
                if (newX >= 0 && newX < 8) {
                    moves.add(new Move(newX, yp, new Square(newX, yp)));
                }
            }

            // Vertical moves
            for (int dy = -1; dy <= 1; dy++) {
                int newY = yp + dy;
                if (newY >= 0 && newY < 8) {
                    moves.add(new Move(xp, newY, new Square(xp, newY)));
                }
            }

            // Diagonal moves
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) continue; // don't move to the same square
                    int newX = xp + dx;
                    int newY = yp + dy;
                    if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                        moves.add(new Move(newX, newY, new Square(newX, newY)));
                    }
                }
            }

            return moves;
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Queen can move any number of squares in any direction (horizontally, vertically, or diagonally)
        int dx = Math.abs(newX - xp);
        int dy = Math.abs(newY - yp);
        return (dx == 0 && dy != 0) || (dx != 0 && dy == 0) || (dx == dy);
    }

    @Override
    public boolean isUnderAttack() {
        // Check if any opponent piece can capture the queen
        for (Piece p : ps) {
            if (p.isWhite != isWhite && p.isValidMove(xp, yp)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canCastle() {
        // Queen cannot castle
        return false;
    }
}
    

class Bishop extends Piece {
    Bishop(int xp, int yp, boolean isWhite, LinkedList<Piece> ps) {
        super(xp, yp, isWhite, "bishop", ps);
    }

    @Override
    public List<Move> getPossibleMoves() {
        List<Move> moves = new ArrayList<>();

        // Diagonal moves up and to the right
        int dx = 1;
        int dy = 1;
        while (xp + dx < 8 && yp + dy < 8) {
            if (isSquareOccupied(xp + dx, yp + dy)) {
                if (isOpponentPiece(xp + dx, yp + dy)) {
                    moves.add(new Move(xp + dx, yp + dy, new Square(xp + dx, yp + dy)));
                }
                break;
            } else {
                moves.add(new Move(xp + dx, yp + dy, new Square(xp + dx, yp + dy)));
            }
            dx++;
            dy++;
        }

        // Diagonal moves up and to the left
        dx = 1;
        dy = -1;
        while (xp + dx < 8 && yp + dy >= 0) {
            if (isSquareOccupied(xp + dx, yp + dy)) {
                if (isOpponentPiece(xp + dx, yp + dy)) {
                    moves.add(new Move(xp + dx, yp + dy, new Square(xp + dx, yp + dy)));
                }
                break;
            } else {
                moves.add(new Move(xp + dx, yp + dy, new Square(xp + dx, yp + dy)));
            }
            dx++;
            dy--;
        }

        // Diagonal moves down and to the right
        dx = -1;
        dy = 1;
        while (xp + dx >= 0 && yp + dy < 8) {
            if (isSquareOccupied(xp + dx, yp + dy)) {
                if (isOpponentPiece(xp + dx, yp + dy)) {
                    moves.add(new Move(xp + dx, yp + dy, new Square(xp + dx, yp + dy)));
                }
                break;
            } else {
                moves.add(new Move(xp + dx, yp + dy, new Square(xp + dx, yp + dy)));
            }
            dx--;
            dy++;
        }

        // Diagonal moves down and to the left
        dx = -1;
        dy = -1;
        while (xp + dx >= 0 && yp + dy >= 0) {
            if (isSquareOccupied(xp + dx, yp + dy)) {
                if (isOpponentPiece(xp + dx, yp + dy)) {
                    moves.add(new Move(xp + dx, yp + dy, new Square(xp + dx, yp + dy)));
                }
                break;
            } else {
                moves.add(new Move(xp + dx, yp + dy, new Square(xp + dx, yp + dy)));
            }
            dx--;
            dy--;
        }

        return moves;
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Bishop can move any number of squares diagonally
        int dx = Math.abs(newX - xp);
        int dy = Math.abs(newY - yp);
        return dx == dy;
    }

    @Override
    public boolean isUnderAttack() {
        // Check if any opponent piece can capture the bishop
        for (Piece p : ps) {
            if (p.isWhite != isWhite && p.isValidMove(xp, yp)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSquareOccupied(int x, int y) {
        // Check if a square is occupied by a piece
        for (Piece p : ps) {
            if (p.xp == x && p.yp == y) {
                return true;
            }
        }
        return false;
    }

    private boolean isOpponentPiece(int x, int y) {
        // Check if a piece on a square is an opponent piece
        for (Piece p : ps) {
            if (p.xp == x && p.yp == y && p.isWhite != isWhite) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canCastle() {
        return false; // bishop cannot castle
    }
}

class Rook extends Piece {
    Rook(int xp, int yp, boolean isWhite, LinkedList<Piece> ps) {
        super(xp, yp, isWhite, "rook", ps);
    }

    @Override
    public List<Move> getPossibleMoves() {
        List<Move> moves = new ArrayList<>();

        // Rook can move horizontally or vertically any number of squares
        // Horizontal moves
        for (int dx = -1; dx <= 1; dx++) {
            if (dx == 0) continue; // don't move to the same square
            int newX = xp + dx;
            while (newX >= 0 && newX < 8) {
                if (isSquareOccupied(newX, yp)) {
                    if (isOpponentPiece(newX, yp)) {
                        moves.add(new Move(newX, yp, new Square(newX, yp)));
                    }
                    break;
                } else {
                    moves.add(new Move(newX, yp, new Square(newX, yp)));
                }
                newX += dx;
            }
        }

        // Vertical moves
        for (int dy = -1; dy <= 1; dy++) {
            if (dy == 0) continue; // don't move to the same square
            int newY = yp + dy;
            while (newY >= 0 && newY < 8) {
                if (isSquareOccupied(xp, newY)) {
                    if (isOpponentPiece(xp, newY)) {
                        moves.add(new Move(xp, newY, new Square(xp, newY)));
                    }
                    break;
                } else {
                    moves.add(new Move(xp, newY, new Square(xp, newY)));
                }
                newY += dy;
            }
        }

        return moves;
    }

    private boolean isSquareOccupied(int x, int y) {
        for (Piece p : ps) {
            if (p.xp == x && p.yp == y) {
                return true;
            }
        }
        return false;
    }

    private boolean isOpponentPiece(int x, int y) {
        for (Piece p : ps) {
            if (p.xp == x && p.yp == y && p.isWhite != isWhite) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isValidMove(int newX, int newY) {
        // Rook can move horizontally or vertically any number of squares
        int dx = Math.abs(newX - xp);
        int dy = Math.abs(newY - yp);
        return (dx == 0 && dy != 0) || (dx != 0 && dy == 0);
    }

    @Override
    public boolean canCastle() {
        // Rooks cannot castle
        return false;
    }
}


class Knight extends Piece {
        Knight(int xp, int yp, boolean isWhite, LinkedList<Piece> ps) {
            super(xp, yp, isWhite, "knight", ps);
        }
    
        @Override
        public List<Move> getPossibleMoves() {
            List<Move> moves = new ArrayList<>();
    
            // Knight moves in an L-shape (two squares in one direction, then one square to the side)
            int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};
            int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    
            for (int i = 0; i < 8; i++) {
                int newX = xp + dx[i];
                int newY = yp + dy[i];
    
                if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                    if (!isSquareOccupiedBySameColor(newX, newY)) {
                        moves.add(new Move(newX, newY, new Square(newX, newY)));
                    }
                }
            }
    
            return moves;
        }
    
        @Override
        public boolean isValidMove(int newX, int newY) {
            // Knight moves in an L-shape (two squares in one direction, then one square to the side)
            int dx = Math.abs(newX - xp);
            int dy = Math.abs(newY - yp);
            return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
        }
    
        @Override
        public boolean isUnderAttack() {
            // Check if any opponent piece can capture the knight
            for (Piece p : ps) {
                if (p.isWhite != isWhite && p.isValidMove(xp, yp)) {
                    return true;
                }
            }
            return false;
        }
    
        private boolean isSquareOccupiedBySameColor(int x, int y) {
            // Check if a square is occupied by a piece of the same color
            for (Piece p : ps) {
                if (p.xp == x && p.yp == y && p.isWhite == isWhite) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean canCastle() {
            // Knights cannot castle
            return false;
        }
    }

    class Pawn extends Piece {
        Pawn(int xp, int yp, boolean isWhite, LinkedList<Piece> ps) {
            super(xp, yp, isWhite, "pawn", ps);
        }
    
        @Override
        public List<Move> getPossibleMoves() {
            List<Move> moves = new ArrayList<>();
    
            // Pawn can move forward one square, but captures diagonally one square
            if (isWhite) {
                // White pawn moves up
                if (yp < 7) {
                    if (!isSquareOccupied(xp, yp + 1)) {
                        moves.add(new Move(xp, yp + 1, new Square(xp, yp + 1)));
                    }
                    if (yp == 1 && !isSquareOccupied(xp, yp + 2)) {
                        moves.add(new Move(xp, yp + 2, new Square(xp, yp + 2)));
                    }
                }
                // Capturing diagonally
                if (xp > 0 && yp < 7) {
                    if (isOpponentPiece(xp - 1, yp + 1)) {
                        moves.add(new Move(xp - 1, yp + 1, new Square(xp - 1, yp + 1)));
                    }
                }
                if (xp < 7 && yp < 7) {
                    if (isOpponentPiece(xp + 1, yp + 1)) {
                        moves.add(new Move(xp + 1, yp + 1, new Square(xp + 1, yp + 1)));
                    }
                }
            } else {
                // Black pawn moves down
                if (yp > 0) {
                    if (!isSquareOccupied(xp, yp - 1)) {
                        moves.add(new Move(xp, yp - 1, new Square(xp, yp - 1)));
                    }
                    if (yp == 6 && !isSquareOccupied(xp, yp - 2)) {
                        moves.add(new Move(xp, yp - 2, new Square(xp, yp - 2)));
                    }
                }
                // Capturing diagonally
                if (xp > 0 && yp > 0) {
                    if (isOpponentPiece(xp - 1, yp - 1)) {
                        moves.add(new Move(xp - 1, yp - 1, new Square(xp - 1, yp - 1)));
                    }
                }
                if (xp < 7 && yp > 0) {
                    if (isOpponentPiece(xp + 1, yp - 1)) {
                        moves.add(new Move(xp + 1, yp - 1, new Square(xp + 1, yp - 1)));
                    }
                }
            }
    
            return moves;
        }
    
        @Override
        public boolean isValidMove(int newX, int newY) {
            // Pawn can move forward one square, but captures diagonally one square
            int dx = Math.abs(newX - xp);
            int dy = Math.abs(newY - yp);
            if (isWhite) {
                return (dx == 0 && dy == 1) || (dx == 1 && dy == 1);
            } else {
                return (dx == 0 && dy == -1) || (dx == 1 && dy == -1);
            }
        }
    
        @Override
        public boolean isUnderAttack() {
            // Check if any opponent piece can capture the pawn
            for (Piece p : ps) {
                if (p.isWhite != isWhite && p.isValidMove(xp, yp)) {
                    return true;
                }
            }
            return false;
        }
    
        private boolean isSquareOccupied(int x, int y) {
            // Check if a square is occupied by a piece
            for (Piece p : ps) {
                if (p.xp == x && p.yp == y) {
                    return true;
                }
            }
            return false;
        }
    
        private boolean isOpponentPiece(int x, int y) {
            // Check if a piece on a square is an opponent piece
            for (Piece p : ps) {
                if (p.xp == x && p.yp == y && p.isWhite != isWhite) {
                    return true;
                }
            }
            return false;
        }


        @Override
        public boolean canCastle() {
            return false; // Pawns cannot castle
        }
    }



    