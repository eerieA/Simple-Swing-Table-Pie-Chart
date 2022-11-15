package ui;

import model.ListOfStocks;
import model.Stock;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class PieChart extends JComponent {
    private int pieWidth;
    private ListOfStocks los;
    private ArrayList<Integer> testPercents;
    private ArrayList<Float> percents;

    // REQUIRES: pieWidth >= 0
    // EFFECTS: instantiate an empty PieChart, but with data acquired from outside this class
    public PieChart(int pieWidth) {
        StockDataHandler stockDataHandler = new StockDataHandler();

        this.los = stockDataHandler.getListOfStocks();

        this.testPercents = new ArrayList<>();
        testPercents.add(1001);
        testPercents.add(200);
        //testValues.add(300);
        this.percents = convertToPercents(extractInvestValues(los));

        this.pieWidth = pieWidth;
    }

    // MODIFIES: this
    // EFFECTS: paint some Arc2D shapes in a Graphics2D object, so that the final graphic looks like
    //          a pie chart, with its sector areas correctly representing the input data
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D pieBuilder = (Graphics2D) g.create();

        buildSectors(pieBuilder, this.percents);
        pieBuilder.dispose();
        super.paintComponent(g);
    }

    // MODIFIES: builder (input variable)
    // EFFECTS: Construct sector arcs based on input percent values, and add them to the input Graphics2D
    private void buildSectors(Graphics2D builder, ArrayList<Float> percents) {
        Color colorStart = new Color(84, 180, 53);

        if (percents.size() <= 1) {
            // This means there is no stock, because either percents is empty or only has the
            // default one percent ratio .0f
            Area area = createArea(.0f, 1.0f);
            builder.setColor(Color.BLACK);
            builder.fill(area);
        } else {
            // This means there is at least one stock
            int i = 2;

            // Paint the 1st area from .0f to the first non-zero percent value, which is percents.get(1)
            Area areaStart = createArea(.0f, percents.get(1));
            builder.setColor(colorStart);
            builder.fill(areaStart);

            // Paint the rest from i = 2; if percents.size() is 2, then obviously this loop will be skipped
            while (i <= percents.size() - 1) {
                Area area = createArea((percents.get(i - 2) + percents.get(i - 1)), percents.get(i));
                builder.setColor(new Color(colorStart.getRed() + i * 8,
                        colorStart.getGreen() + i * 8,
                        colorStart.getBlue() + i * 8));
                builder.fill(area);
                i++;
            }
        }
    }

    // REQUIRES: start >= 0, extent>= 0
    // EFFECTS: Create a sector as an Arc2D shape, using input parameters
    private Area createArea(float start, float extent) {
        float fromAngle = 90 - start * 360;
        float toAngle = (extent * 360) * (-1);
        Area area = new Area(new Arc2D.Double((float) pieWidth / 6, (float) pieWidth / 6,
                (float) pieWidth / 2, (float) pieWidth / 2, fromAngle, toAngle, Arc2D.PIE));
        return area;
    }

    // EFFECTS: convert every Integer in the input list, then return result as a new ArrayList<Float>
    private ArrayList<Float> convertToPercents(ArrayList<Integer> intList) {
        ArrayList<Float> floatList = new ArrayList<>();

        // Add a .0f first
        floatList.add(.0f);

        // Convert input ArrayList to array, then use stream to collect and calculate sum
        Object[] intListArray = intList.toArray();
        IntStream intStream = Arrays.stream(intListArray).mapToInt(i -> (int) i);
        int intSum = intStream.sum();
        int i = 0;

        // Calculate every int *except the last one* into a float in [0, 1.0)
        while (i <= intList.size() - 2) {
            float f = (float) intList.get(i) / intSum;
            floatList.add(f);
            i++;
        }

        // Calculate the last one float, so that the total will not exceed 1.0
        Object[] floatListArray = floatList.toArray();
        DoubleStream doubleStream = Arrays.stream(floatListArray).mapToDouble(f -> (float) f);
        Double floatSum = doubleStream.sum();
        floatList.add(1.0f - floatSum.floatValue());

        return floatList;
    }

    // EFFECTS: Extract only investedAmount value from list of stocks, return it as an ArrayList<Integer>
    private ArrayList<Integer> extractInvestValues(ListOfStocks los) {
        ArrayList<Integer> result = new ArrayList<>();

        if (los == null) {
            return result;
        } else {
            for (Stock st: los.getStocks()) {
                result.add(st.getInvestedAmount());
            }
        }

        return result;
    }
}
