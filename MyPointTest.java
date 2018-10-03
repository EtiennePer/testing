package main.fr.ut2j.m1ice.ootesting;

import main.fr.ut2j.m1ice.ootesting.MyPoint;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static java.lang.Math.atan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyPointTest {

    private static MyPoint myPoint = null;
    private static final double DELTA = 1e-15;

    @Mock
    private Random rdmock1;
    @Mock
    private Random rdmock2;
    @Mock
    private ITranslation translationMock;

    @Before
    public void setUp() {
        // Instantiation de myPoint
        myPoint = new MyPoint();
    }

    @After
    public void tearDown() {
    }

    /**
     * Permet de tester le constructeur par défaut (sans paramètres).
     * <p>
     * Les coordonnées du point par défaut doivent être (0,0)
     */
    @Test
    public void testmyPoint1() {

        // Prepare
        double expected_x = 0;
        double expected_y = 0;

        // Result
        assertEquals(expected_x, myPoint.getX(), DELTA);
        assertEquals(expected_y, myPoint.getY(), DELTA);
    }

    /**
     * Permet de tester le constructeur avec paramètre x et y.
     * <p>
     * Les coordonnées du point doivent être semblable à celles
     * passées dans les paramètres du constructeur
     */
    @Test
    public void testmyPoint2() {
        // Prepare
        double expected_x = 190;
        double expected_y = 46;

        // Test
        MyPoint actual = new MyPoint(expected_x, expected_y);

        // Result
        assertEquals(expected_x, actual.getX(), DELTA);
        assertEquals(expected_y, actual.getY(), DELTA);
    }

    /**
     * Permet de tester le constructeur avec paramètre un objet MyPoint par défaut.
     * <p>
     * Les coordonnées du point doivent être semblable à celles
     * du point par défaut (soit (0,0))
     */
    @Test
    public void testmyPoint3() {
        // Prepare
        double expected_x = myPoint.getX();
        double expected_y = myPoint.getY();

        // Test
        MyPoint actual = new MyPoint(myPoint);

        // Result
        assertEquals(expected_x, actual.getX(), DELTA);
        assertEquals(expected_y, actual.getY(), DELTA);
    }

    /**
     * Permet de tester le constructeur avec paramètre un objet MyPoint.
     * <p>
     * Les coordonnées du point doivent être semblable à celles
     * du point donné en paramètres.
     */
    @Test
    public void testmyPoint4() {
        // Prepare
        myPoint.setX(1230);
        myPoint.setY(849);
        double expected_x = myPoint.getX();
        double expected_y = myPoint.getY();

        // Test
        MyPoint actual = new MyPoint(myPoint);

        // Result
        assertEquals(expected_x, actual.getX(), DELTA);
        assertEquals(expected_y, actual.getY(), DELTA);
    }

    /**
     * Permet de tester le constructeur avec paramètre un objet MyPoint.
     *
     * Dans le cas ou un point null est donné, le constructeur
     * doit affecter les valeurs par défaut 0,0
     */
    @Test
    public void testmyPoint5() {
        // Prepare
        double expected_x = 0;
        double expected_y = 0;

        // Test
        MyPoint actual = new MyPoint(null);

        // Result
        assertEquals(expected_x, actual.getX(), DELTA);
        assertEquals(expected_y, actual.getY(), DELTA);
    }

    /**
     * Test les méthodes getX et setX. Les valeurs par défaut des coordonnées doivent
     * être respectées.
     * Les assignations doivent mettre à jour les valeurs comme précisé
     * dans les spécifications.
     */
    @Test
    public void testgetXsetX1() {
        // test default value
        assertEquals(0, myPoint.getX(), DELTA);

        double expected_x = 156;
        myPoint.setX(expected_x);

        // test modified value
        assertEquals(expected_x, myPoint.getX(), DELTA);
    }

    /**
     * Test les méthodes getY et setY. Les valeurs par défaut des coordonnées doivent
     * être respectées.
     * Les assignations doivent mettre à jour les valeurs comme précisé
     * dans les spécifications.
     */
    @Test
    public void testgetYsetY1() {
        // test default value
        assertEquals(0, myPoint.getY(), DELTA);

        double expected_y = 145;
        myPoint.setY(expected_y);

        // test modified value
        assertEquals(expected_y, myPoint.getY(), DELTA);
    }

    /**
     * Test les méthodes getY et setY avec
     * la valeur Double.NaN qui est interdite.
     * <p>
     * Lorsque cette valeur est utilisée aucune donnée ne doît être modifié
     */
    @Test
    public void testgetYsetY2() {
        // Save Y state
        double expected_y = 140;
        myPoint.setY(expected_y);

        myPoint.setY(Double.NaN);

        assertEquals(expected_y, expected_y, DELTA);
    }

    /**
     * Test les méthodes getX et setX avec
     * la valeur Double.NaN qui est interdite.
     * <p>
     * Lorsque cette valeur est utilisée aucune donnée ne doît être modifié
     */
    @Test
    public void testgetXsetX2() {
        // Save X state
        double expected_x = 13;
        myPoint.setX(expected_x);

        myPoint.setX(Double.NaN);

        assertEquals(expected_x, myPoint.getX() , DELTA);
    }

    /**
     * Test la méthode testScale.
     * Le facteur de l'échelle doit être respecté
     * et les valeurs mises à jour en conséquences.
     */
    @Test
    public void testScale1() {
        // Attribut des valeurs de base au point
        myPoint.setY(10);
        myPoint.setX(6);
        double scale_factor = 2;
        double expected_x = scale_factor * myPoint.getX();
        double expected_y = scale_factor * myPoint.getY();

        MyPoint actual = myPoint.scale(scale_factor);

        assertEquals(expected_x, actual.getX(), DELTA);
        assertEquals(expected_y, actual.getY(), DELTA);
    }

    /**
     * Lors de l'utilisation de la méthode  horizontalSymetrie
     * avec un paramètre null. La méthode doit lever une
     * exception du type IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void testhorizontalSymmetry1() throws IllegalArgumentException{
        myPoint.horizontalSymmetry(null);
    }

    /**
     * Lors de l'utilisation de la méthode  horizontalSymetrie
     * avec un paramètre non null.
     *
     * Le
     */
    @Test
    public void testhorizontalSymmetry2(){

        MyPoint origin = new MyPoint(10,10);
        double expected_x = 2d * origin.getX() - myPoint.getX();
        double expected_y = myPoint.getY();

        MyPoint actual = myPoint.horizontalSymmetry(origin);

        assertEquals(expected_x, actual.getX(), DELTA);
        assertEquals(expected_y, myPoint.getY(), DELTA);
    }

    /**
     * Cette méthode doit retourner une Exception
     * car l'utilisation du paramètre null n'est pas valide
     *
     */
    @Test (expected = IllegalArgumentException.class)
    public void testcentralSymmetryNULL ( )
    {
        new MyPoint ( 10 , 20 ) . centralSymmetry ( null ) ;
    }

    /**
     * L'objectif de ce test est de vérifier
     * que les valeurs de x et y sont modifiées
     * pour le point.
     *
     * On utilise des mock de random pour fixer les valeurs
     */
    @Test
    public void testsetPoint1()
    {
        // Configuring random mocks
        int expected_x = 10;
        int expected_y = 405;
        Mockito.when(rdmock1.nextInt()).thenReturn(expected_x);
        Mockito.when(rdmock2.nextInt()).thenReturn(expected_y);

        myPoint.setPoint(rdmock1,rdmock2);

        assertEquals(expected_x, myPoint.getX(), DELTA);
        assertEquals(expected_y, myPoint.getY(), DELTA);
        verify(rdmock1, times(1)).nextInt();
        verify(rdmock2, times(1)).nextInt();
    }

    @Test
    public void testTranslation1()
    {
        // Configuring the mock
        int expected_x = 10;
        int expected_y = 20;
        when(translationMock.getTx()).thenReturn(expected_x);
        when(translationMock.getTy()).thenReturn(expected_y);

        // Should translate the point from 10 & 20
        myPoint.translate(translationMock);

        assertEquals(expected_x, myPoint.getX(), DELTA);
        assertEquals(expected_y, myPoint.getY(), DELTA);
        verify(translationMock, times(1)).getTx();
        verify(translationMock, times(1)).getTy();
    }

    @Test
    public void testcomputeAngle1()
    {
       double actual = myPoint.computeAngle(null);

       assertTrue(Double.isNaN(actual));
    }

    @Test
    public void testcomputeAngle2()
    {
        double actual = myPoint.computeAngle(myPoint);

        assertEquals(Math.PI / 3d, actual, DELTA);
    }

    @Test
    public void testcomputeAngle3()
    {
        MyPoint point = new MyPoint(0, -12);
        double actual = myPoint.computeAngle(point);

        assertEquals(Math.PI * 2d - Math.PI / 3d, actual, DELTA);
    }

    @Test
    public void testcomputeAngle4()
    {
        MyPoint point = new MyPoint(15, 12);
        double actual = myPoint.computeAngle(point);

        assertEquals(atan(Double.valueOf(point.getY() - myPoint.getY()) / Double.valueOf(point.getX() - myPoint.getX()))
                , actual, DELTA);
    }

    @Test
    public void testcomputeAngle5()
    {
        MyPoint point = new MyPoint(-17, 12);
        double actual = myPoint.computeAngle(point);

        assertEquals(Math.PI - atan(-Double.valueOf(point.getY() - myPoint.getY()) / Double.valueOf(point.getX() - myPoint.getX()))
                , actual, DELTA);
    }

    @Test
    public void testgetMiddlePoint1()
    {
        MyPoint secondPoint = new MyPoint(10,10);
        MyPoint actual = myPoint.getMiddlePoint(secondPoint);

        assertEquals(5, actual.getX(), DELTA);
        assertEquals(5, actual.getY(), DELTA);
    }
}