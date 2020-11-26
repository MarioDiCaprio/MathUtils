package mathUtils.plot.complex;

import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import mathUtils.calculus.MathTools;
import mathUtils.calculus.complex.Complex;
import mathUtils.calculus.complex.ComplexFunction;
import mathUtils.calculus.complex.ComplexMath;
import mathUtils.calculus.complex.annot.ComplexReal;
import mathUtils.linear.Point;

@Deprecated
public class RiemannSurface extends ComplexPlotMethod {

    /////////////////////////////////////////////////
    ///// fields
    /////////////////////////////////////////////////

    private double[] xDomain = new double[] {-5, 5};
    private double[] yDomain = new double[] {-5, 5};
    private double[] zDomain = new double[] {-5, 5};

    @ComplexReal
    ComplexFunction accuracy;


    /////////////////////////////////////////////////
    ///// constructor
    /////////////////////////////////////////////////

    public RiemannSurface(ComplexCSystem c) {
        super(c);
        setAccuracyFunction(z -> {
            double x = MathTools.log(ComplexMath.abs(z), 2);
            x  -= Math.floor(x);
            return MathTools.clamp(x, 0.01, 100);
        });
    }


    /////////////////////////////////////////////////
    ///// methods
    /////////////////////////////////////////////////


    public double[] getXDomain() {
        return xDomain;
    }

    public RiemannSurface setXDomain(double from, double to) {
        xDomain[0] = from;
        xDomain[1] = to;
        return this;
    }

    public double[] getYDomain() {
        return yDomain;
    }

    public RiemannSurface setYDomain(double from, double to) {
        yDomain[0] = from;
        yDomain[1] = to;
        return this;
    }

    public double[] getZDomain() {
        return zDomain;
    }

    public RiemannSurface setZDomain(double from, double to) {
        xDomain[0] = from;
        xDomain[1] = to;
        return this;
    }



    public RiemannSurface setAccuracyFunction(@ComplexReal  ComplexFunction f) {
        accuracy = f;
        return this;
    }

    public ComplexFunction getAccuracyFunction() {
        return accuracy;
    }


    private double visibleDigits(String domain) {
        domain = domain.toLowerCase();
        switch (domain) {
            case "x":
                return Math.abs(xDomain[0] - xDomain[1]);
            case "y":
                return Math.abs(yDomain[0] - yDomain[1]);
            case "z":
                return Math.abs(zDomain[0] - zDomain[1]);
            default:
                return 0;
        }
    }


    private PerspectiveCamera addCamera(Scene scene) {
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(false);
        scene.setCamera(perspectiveCamera);
        return perspectiveCamera;
    }


    private Point origin() {
        return new Point(
                MathTools.mean(xDomain),
                MathTools.mean(yDomain),
                MathTools.mean(zDomain)
        );
    }



    private Transform[] rotation(double x, double y, double z) {
        Point org = origin();

        Rotate rx = new Rotate();
        rx.setPivotX( org.getX() );
        rx.setPivotY( org.getY() );
        rx.setPivotZ( org.getZ() );
        rx.setAxis(Rotate.X_AXIS);
        rx.setAngle(x);

        Rotate ry = new Rotate();
        ry.setPivotX( org.getX() );
        ry.setPivotY( org.getY() );
        ry.setPivotZ( org.getZ() );
        ry.setAxis(Rotate.Y_AXIS);
        ry.setAngle(y);

        Rotate rz = new Rotate();
        rz.setPivotX( org.getX() );
        rz.setPivotY( org.getY() );
        rz.setPivotZ( org.getZ() );
        rz.setAxis(Rotate.Z_AXIS);
        rz.setAngle(z);

        return new Transform[] {rx, ry, rz};
    }


    public WritableImage scale(Image source, int targetWidth, int targetHeight, boolean preserveRatio) {
        ImageView imageView = new ImageView(source);
        imageView.setPreserveRatio(preserveRatio);
        imageView.setFitWidth(targetWidth);
        imageView.setFitHeight(targetHeight);
        return imageView.snapshot(null, null);
    }


    @Override
    void paintSelf() {
        ComplexFunction f = getPlottedFunction();

        double x = getCanvas().getTranslateX();
        double y = getCanvas().getTranslateY();
        double width = getCanvas().getWidth();
        double height = getCanvas().getHeight();
        int squares = 50;
        double stepX = visibleDigits("x") / squares;
        double stepY = visibleDigits("y") / squares;
        TriangleMesh mesh = new TriangleMesh();

        int count = 0;

        for (float r1 = (float) xDomain[0]; r1 <= xDomain[1] - stepX; r1 += stepX) {
            float r2 = (float) (r1 + stepX);

            for (float i1 = (float) yDomain[0]; i1 <= yDomain[1] - stepY; i1 += stepY) {
                float i2 = (float) (i1 + stepY);

                Number z1 = f.z( new Complex(r1, i1) );
                Number z2 = f.z( new Complex(r2, i1) );
                Number z3 = f.z( new Complex(r2, i2) );
                Number z4 = f.z( new Complex(r1, i2) );

                count += 4;

                //System.out.println("re: " + r1 + ";  im: " + i1 + ";  |z|: " + ComplexMath.abs(z1));

                float[] points = new float[] {
                        r1, i1, (float) ComplexMath.abs(z1),
                        r2, i1, (float) ComplexMath.abs(z2),
                        r2, i2, (float) ComplexMath.abs(z3),
                        r1, i2, (float) ComplexMath.abs(z4)
                };

                for (int n=0; n<points.length; n++) {
                    if ( Float.isNaN(points[n]) )
                        points[n] = 0;
                }

                float[] texCoords = {
                        0, 0, // idx t0
                        0, 1, // idx t1
                        1, 1, // idx t2
                        1, 0  // idx t3
                };

                int[] faces = new int[] {
                        count-4, count-4, count-3, count-3, count-1, count-1,
                        count-3, count-3, count-2, count-2, count-1, count-1
                };

                mesh.getPoints().addAll(points);
                mesh.getTexCoords().addAll(texCoords);
                mesh.getFaces().addAll(faces);
            }
        }

        MeshView meshView = new MeshView(mesh);
        meshView.setMaterial(new PhongMaterial(Color.BISQUE));

        meshView.setTranslateX(250);
        meshView.setTranslateY(300);

        //rotation
        meshView.getTransforms().addAll(
                rotation(90, 0, 45)
        );

        meshView.setScaleX(1);
        meshView.setScaleY(1);
        meshView.setScaleZ(1);

        meshView.setCullFace(CullFace.NONE);

        SnapshotParameters sp = new SnapshotParameters();
        sp.setTransform( Transform.scale(width, height) );


        Scene scene = new Scene(new Group(meshView), width, height);

        WritableImage img = scale(meshView.snapshot(sp, null), 500, 500, false);
        getCanvas().getGraphicsContext2D().drawImage(img, x, y);
    }


    public Pane plotSettings() {
        VBox root = new VBox();
        double width = 50;
        double height = 25;

        HBox domX = new HBox();

        TextField domX0 = new TextField("-10");
        domX0.setMinSize(width, height);
        TextField domX1 = new TextField("10");
        domX1.setMinSize(width, height);

        domX0.setOnKeyTyped(e -> {
            double x0 = Double.parseDouble( domX0.getText() );
            double x1 = Double.parseDouble( domX1.getText() );
            setXDomain(x0, x1);
            paintSelf();
        });
        domX1.setOnKeyTyped(e -> {
            double x0 = Double.parseDouble( domX0.getText() );
            double x1 = Double.parseDouble( domX1.getText() );
            setXDomain(x0, x1);
            paintSelf();
        });

        domX.getChildren().addAll(domX0, domX1);
        root.getChildren().addAll(new Label("X-Domain"), domX);


        HBox domY = new HBox();

        TextField domY0 = new TextField("-10");
        domY0.setMinSize(width, height);
        TextField domY1 = new TextField("10");
        domY1.setMinSize(width, height);

        domY0.setOnKeyTyped(e -> {
            double y0 = Double.parseDouble( domY0.getText() );
            double y1 = Double.parseDouble( domY1.getText() );
            setYDomain(y0, y1);
            paintSelf();
        });
        domY1.setOnKeyTyped(e -> {
            double y0 = Double.parseDouble( domY0.getText() );
            double y1 = Double.parseDouble( domY1.getText() );
            setYDomain(y0, y1);
            paintSelf();
        });

        domY.getChildren().addAll(domY0, domY1);
        root.getChildren().addAll(new Label("Y-Domain"), domY);


        return root;
    }

}
