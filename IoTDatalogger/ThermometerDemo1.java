
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.JThermometer;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.net.URI;
import java.net.URISyntaxException;
/**
 * A demonstration application for the thermometer plot.
 *
 * @author Bryan Scott
 */
public class ThermometerDemo1 extends JPanel {

    /** Options for the value label position combo box. */
    protected static final String[] OPTIONS = {
            "None", "Right", "Left", "Bulb"};

    /** Axis position options. */
    protected static final String[] AXIS_OPTIONS = {
            "None", "Right", "Left"};

    /** The dataset. */
    private DefaultValueDataset data = new DefaultValueDataset(20.0);

    /** The meter plot (dial). */
    private MeterPlot meterplot = new MeterPlot(this.data);

    /** The meter chart (dial). */
    private JFreeChart meterchart = new JFreeChart("Meter Chart",
            JFreeChart.DEFAULT_TITLE_FONT,
            this.meterplot, false);

    /** The meter panel. */
    private ChartPanel panelMeter = new ChartPanel(this.meterchart);

    /** Panel 1. */
    private JPanel jPanel1 = new JPanel();

    /** Increment button for thermometer ?. */
    private JButton butUp3 = new JButton();

    /** Decrement button for thermometer ?. */
    private JButton butDown3 = new JButton();

    /** Panel 2. */
    private JPanel jPanel2 = new JPanel();

    /** Borderlayout 2. */
    private BorderLayout borderLayout2 = new BorderLayout();

    /** Panel 3. */
    private JPanel jPanel3 = new JPanel();

    /** Borderlayout 3. */
    private BorderLayout borderLayout3 = new BorderLayout();

    /** Panel 4. */
    private JPanel jPanel4 = new JPanel();

    /** Decrement button for thermometer ?. */
    private JButton butDown2 = new JButton();

    /** Increment button for thermometer ?. */
    private JButton butUp2 = new JButton();

    /** Panel 5. */
    private JPanel jPanel5 = new JPanel();

    /** Grid layout 1. */
    private GridLayout gridLayout1 = new GridLayout();

    /** Panel 6. */
    private JPanel jPanel6 = new JPanel();

    /** Increment button for thermometer ?. */
    private JButton butUp1 = new JButton();

    /** Decrement button for thermometer ?. */
    private JButton butDown1 = new JButton();

    /** Thermometer 1. */
    private JThermometer thermo1 = new JThermometer();

    /** Thermometer 2. */
    private JThermometer thermo2 = new JThermometer();

    /** Thermometer 2. */
    private JThermometer thermo3 = new JThermometer();

    /** Array of thermometers. */
    private JThermometer[] thermo = new JThermometer[3];

    /** Borderlayout 1. */
    private BorderLayout borderLayout1 = new BorderLayout();

    /** Panel 7. */
    private JPanel jPanel7 = new JPanel();

    /** Panel 8. */
    private JPanel jPanel8 = new JPanel();

    /** Panel 9. */
    private JPanel jPanel9 = new JPanel();

    /** Grid layout 2. */
    private GridLayout gridLayout2 = new GridLayout();

    /** Grid layout 3. */
    private GridLayout gridLayout3 = new GridLayout();

    /** Grid layout 4. */
    private GridLayout gridLayout4 = new GridLayout();

    /** Combo box 1 for value label position. */
    private JComboBox pickShow0 = new JComboBox(OPTIONS);

    /** Combo box 2 for value label position. */
    private JComboBox pickShow1 = new JComboBox(OPTIONS);

    /** Combo box 3 for value label position. */
    private JComboBox pickShow2 = new JComboBox(OPTIONS);

    /** Combo box 1 for axis position. */
    private JComboBox pickAxis0 = new JComboBox(AXIS_OPTIONS);
    /** Combo box 2 for axis position. */
    private JComboBox pickAxis1 = new JComboBox(AXIS_OPTIONS);
    /** Combo box 3 for axis position. */
    private JComboBox pickAxis2 = new JComboBox(AXIS_OPTIONS);

    /** An array of combo boxes. */
    private JComboBox[] pickShow = new JComboBox[3];

    /** An array of combo boxes. */
    private JComboBox[] pickAxis = new JComboBox[3];

    /** Panel 10. */
    private JPanel jPanel10 = new JPanel();

    /** Borderlayout 4. */
    private BorderLayout borderLayout4 = new BorderLayout();

    /** Panel 11. */
    private JPanel jPanel11 = new JPanel();

    /** Decrement button for thermometer ?. */
    private JButton butDown4 = new JButton();

    /** Increment button for thermometer ?. */
    private JButton butUp4 = new JButton();

    /**
     * Default constructor.
     */
    public ThermometerDemo1() {
        try {
            jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ****************************************************************************
    // * COMMERCIAL SUPPORT / JFREECHART DEVELOPER GUIDE                          *
    // * Please note that commercial support and documentation is available from: *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/support.html                   *
    // *                                                                          *
    // * This is not only a great service for developers, but is a VERY IMPORTANT *
    // * source of funding for the JFreeChart project.  Please support us so that *
    // * we can continue developing free software.                                *
    // ****************************************************************************

    /**
     * Initialises the class.
     *
     * @throws Exception for any exception.
     */
    void jbInit() throws Exception {

        //data.setRange(new Double(-20), new Double(20));
        this.thermo[0] = this.thermo1;
        this.thermo[1] = this.thermo2;
        this.thermo[2] = this.thermo3;

        this.thermo[0].setValue(0.0);
        this.thermo[1].setValue(0.2);
        this.thermo[2].setValue(0.3);

        this.thermo[0].setBackground(Color.white);
        this.thermo[2].setBackground(Color.white);

        this.thermo[0].setOutlinePaint(null);
        this.thermo[1].setOutlinePaint(null);
        this.thermo[2].setOutlinePaint(null);

        this.thermo[2].setUnits(0);
        this.thermo[2].setUnits(1);
        this.thermo[2].setUnits(2);

        //thermo[0].setFont(new Font("SansSerif", Font.BOLD, 20));
        this.thermo[0].setShowValueLines(true);
        this.thermo[0].setFollowDataInSubranges(true);
        //this.thermo[1].setValueLocation(1);

        this.thermo[1].setForeground(Color.BLUE);
        this.thermo[2].setForeground(Color.BLUE);

        Range rg = this.meterplot.getRange();
        this.thermo[0].setRange(10.0, 30.0);
        this.thermo[0].setSubrangeInfo(0, 15, 17.0, -10.0, 22.0);
        this.thermo[0].setSubrangeInfo(1, 17.0, 21.0, 18.0, 26.0);
        this.thermo[0].setSubrangeInfo(2, 21.0, 100.0, 22.0, 40.0);

        this.thermo[1].setRange(10.0, 30.0);
        this.thermo[1].setSubrangeInfo(0, 15, 17.0, -10.0, 22.0);
        this.thermo[1].setSubrangeInfo(1, 17.0, 21.0, 18.0, 26.0);
        this.thermo[1].setSubrangeInfo(2, 21.0, 100.0, 22.0, 40.0);

        this.thermo[2].setRange(10.0, 30.0);
        this.thermo[2].setSubrangeInfo(0, 15, 17.0, -10.0, 22.0);
        this.thermo[2].setSubrangeInfo(1, 17.0, 21.0, 18.0, 26.0);
        this.thermo[2].setSubrangeInfo(2, 21.0, 100.0, 22.0, 40.0);

        this.thermo[0].addSubtitle("T externa", new Font("SansSerif", Font.PLAIN, 16));
        this.thermo[1].addSubtitle("T heater", new Font("SansSerif", Font.PLAIN, 16));
        this.thermo[2].addSubtitle("T Interna", new Font("SansSerif", Font.PLAIN, 16));

        this.thermo[1].setValueFormat(new DecimalFormat("#0.0"));
        this.thermo[2].setValueFormat(new DecimalFormat("#0.00"));

        this.pickShow[0] = this.pickShow0;
        this.pickShow[1] = this.pickShow1;
        this.pickShow[2] = this.pickShow2;

        this.pickAxis[0] = this.pickAxis0;
        this.pickAxis[1] = this.pickAxis1;
        this.pickAxis[2] = this.pickAxis2;

        this.pickAxis[0].setSelectedIndex(2);
        this.pickAxis[1].setSelectedIndex(2);
        this.pickAxis[2].setSelectedIndex(2);

        setLayout(this.gridLayout1);
        this.butDown3.setText("<");
        this.butDown3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setValue(2, -1);
                }
            });
        this.butUp3.setText(">");
        this.butUp3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setValue(2, 1);
                }
            });
        this.jPanel1.setLayout(this.borderLayout2);
        this.jPanel3.setLayout(this.borderLayout3);
        this.butDown2.setText("<");
        this.butDown2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setValue(1, -1);
                }
            });
        this.butUp2.setText(">");
        this.butUp2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setValue(1, 1);
                }
            });
        this.butUp1.setText(">");
        this.butUp1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setValue(0, 1);
                }
            });
        this.butDown1.setText("<");
        this.butDown1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setValue(0, -1);
                }
            });
        this.jPanel5.setLayout(this.borderLayout1);
        this.pickShow0.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setShowValue(0);
                }
            });
        this.pickShow1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setShowValue(1);
                }
            });
        this.pickShow2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setShowValue(2);
                }
            });

        this.pickAxis0.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setShowAxis(0);
                }
            });
        this.pickAxis1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setShowAxis(1);
                }
            });
        this.pickAxis2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setShowAxis(2);
                }
            });

        this.jPanel9.setLayout(this.gridLayout2);
        this.gridLayout2.setColumns(1);
        this.jPanel8.setLayout(this.gridLayout3);
        this.jPanel7.setLayout(this.gridLayout4);
        this.jPanel5.setBorder(BorderFactory.createEtchedBorder());
        this.jPanel3.setBorder(BorderFactory.createEtchedBorder());
        this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
        this.jPanel6.setBackground(Color.white);
        this.jPanel2.setBackground(Color.white);
        this.jPanel9.setBackground(Color.white);
        this.jPanel10.setLayout(this.borderLayout4);
        this.butDown4.setText("<");
        this.butDown4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setMeterValue(-1.1);
                }
            });
        this.butUp4.setText(">");
        this.butUp4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    setMeterValue(1.1);
                }
            });
        this.jPanel1.add(this.thermo3, BorderLayout.CENTER);
        this.jPanel1.add(this.jPanel2, BorderLayout.SOUTH);
        this.jPanel2.add(this.butDown3, null);
        this.jPanel2.add(this.butUp3, null);
        this.jPanel1.add(this.jPanel9, BorderLayout.NORTH);
        this.jPanel9.add(this.pickShow2, null);
        this.jPanel9.add(this.pickAxis2, null);
        add(this.jPanel10, null);
        this.jPanel10.add(this.jPanel11, BorderLayout.SOUTH);
        this.jPanel11.add(this.butDown4, null);
        this.jPanel11.add(this.butUp4, null);
        this.jPanel4.add(this.butDown2, null);
        this.jPanel4.add(this.butUp2, null);
        this.jPanel3.add(this.jPanel8, BorderLayout.NORTH);
        this.jPanel8.add(this.pickShow1, null);
        this.jPanel8.add(this.pickAxis1, null);
        this.jPanel3.add(this.thermo2, BorderLayout.CENTER);
        this.jPanel3.add(this.jPanel4, BorderLayout.SOUTH);
        add(this.jPanel5, null);
        this.jPanel5.add(this.thermo1, BorderLayout.CENTER);
        this.jPanel5.add(this.jPanel6, BorderLayout.SOUTH);
        this.jPanel6.add(this.butDown1, null);
        this.jPanel6.add(this.butUp1, null);
        this.jPanel5.add(this.jPanel7, BorderLayout.NORTH);
        this.jPanel7.add(this.pickShow0, null);
        this.jPanel7.add(this.pickAxis0, null);
        add(this.jPanel3, null);
        add(this.jPanel1, null);
        this.jPanel10.add(this.panelMeter, BorderLayout.CENTER);
    }

    public void setValue(final int thermometer, final double value) {
        if ((thermometer >= 0) && (thermometer < 3)) {
            try {
                this.thermo[thermometer].setValue(
                    this.thermo[thermometer].getValue().doubleValue() + value
                );
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setValueAbs(final int thermometer, final double value) {
        if ((thermometer >= 0) && (thermometer < 3)) {
            try {
                this.thermo[thermometer].setValue(value );
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void setMeterValue(final double value) {
        try {
            double newValue = value;
            if (this.data.getValue() != null) {
                newValue += this.data.getValue().doubleValue();
            }
            this.data.setValue(new Double(newValue));
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void setMeterValueAbs(final double value) {
        try {
            this.data.setValue(value);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void setShowValue(final int thermometer) {
        if ((thermometer >= 0) && (thermometer < 3)) {
            this.thermo[thermometer].setValueLocation(this.pickShow[thermometer].
                getSelectedIndex());
        }
    }

    private void setShowAxis(final int thermometer) {
        if ((thermometer >= 0) && (thermometer < 3)) {
            this.thermo[thermometer].setShowAxisLocation(this.pickAxis[thermometer].
                getSelectedIndex());
        }
    }

}