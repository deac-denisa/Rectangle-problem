import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        ArrayList<Point> points;
        points = readInput();
        if(points != null){
            int rectangleNumber = countRectangles(points);
            System.out.println("number of rectangles = " + rectangleNumber);
        }

    }

    private static ArrayList<Point> readInput() {
        try(BufferedReader bufferedReader = new BufferedReader( new FileReader("input.txt") )) {
            //read one input line
            String inputLine;
            ArrayList<Point> points = new ArrayList<>();

            while((inputLine = bufferedReader.readLine()) != null) {
                //process the input line such that only the numbers(coordinates) remain in the array of strings
                String[] coords = inputLine.replaceAll("[() ]", "").split(",");

                //transform the array of strings into points by parsing them
                for (int i = 0; i < coords.length; i += 2) {
                    float x = Float.parseFloat(coords[i]);
                    float y = Float.parseFloat(coords[i + 1]);
                    points.add(new Point(x, y));
                }
            }
            if(points.isEmpty()){
                System.out.println("Input file is empty.");
                return null;
            }
            return points;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int countRectangles(ArrayList<Point> points){

        HashSet<Point> pointSet = new HashSet<>(points);
        int rectangleNumber = 0;
        Collections.sort(points);

        for(int i = 0; i < points.size(); i++){
            for(int j = i +1; j < points.size(); j++ ){
                //get x and y coordinate of both points for an easier use
                Point point1 = points.get(i);
                Point point2 = points.get(j);

                //check if the points are not on the same vertical or horizontal line
                if ( point1.getX() < point2.getX() && point1.getY() < point2.getY() ){
                    //take the other 2 points of the rectangle, corresponding to the other diagonal
                    Point point3 = new Point(point1.getX(), point2.getY());
                    Point point4 = new Point(point2.getX(), point1.getY());

                    //checks if the those points exist
                    if (pointSet.contains(point3) && pointSet.contains(point4)) {
                        rectangleNumber++;
                    }
                }
            }
        }
        return rectangleNumber;
    }
}

