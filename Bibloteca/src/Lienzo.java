import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
public class Lienzo extends Canvas implements Serializable{
    Lienzo puntero;
    private double[] values={1,4,10}; //Vector x
    private String[] titulos={"Fundamentos de Programacón","Estructura de Datos","Topicos A de Programación"}; //Vector titulos
    private String title="Graficador en canvas"; //Titulo del canvas
    public Lienzo(){}
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D)g;
        this.setBackground(Color.DARK_GRAY);
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial",Font.PLAIN,25));
        g2.drawLine(700, 500, 32, 500);
        g2.drawLine(30, 500, 30, 50);
        if (values == null || values.length == 0) return; //Si no hay valores en el arreglo retorna
        double minValue = 0; //Declaramos varaibles
        double maxValue = 0;
        for (int i = 0; i < values.length; i++) { //Hacemos un recorrido al arreglo
            if (minValue > values[i]) //si el valor minimo es mayor que el valor en la posición del arreglo
                minValue = values[i]; //Entonces el valor minimo es igual al valor de la posición del arreglo
            if (maxValue < values[i]) //si el valor maximo es menor que el valor en la posición del arreglo
                maxValue = values[i]; //Entonces el valor maxima es igual al valor de la posición del arreglo
        }
        Dimension d = getSize(); //Creamos un objeto de tipo dimension y sera igual a su valor por defecto
        int clientWidth = d.width; //Declaramos una variable de la anchura interior de un elemento en pixels
        int clientHeight = d.height; //eclaramos una variable del largo interior de un elemento en pixeles
        int barWidth = clientWidth / values.length; //Hacemos otra varaible que es igual a la barra de la anchura
        Font titleFont = new Font("SansSerif", Font.BOLD, 20); //Creamos un objeto de tipo font para una nueva fuente
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 10); //Le colocamos un font a un nuevo objeto
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont); //obtenemos las métricas de fuente de la fuente actual.
        int titleWidth = titleFontMetrics.stringWidth(title); //Hacemos una variable para buscar el ancho de donde se coloco el titulo
        int y = titleFontMetrics.getAscent(); //Guardamos la coordenda en y
        int x = (clientWidth - titleWidth) / 2; //Y guardamos la coordenada en x, aqui la formula es
        //La variable de la anchura interior - la anchura del titulo entre 2 para tomar la precisión de donde se encuentra en el objeto
        g.setFont(titleFont);//Agregamos la fuente a los graficos
        g.drawString(title, x, y); //Y dibujamos en un graw el titulo y las coordendas
        int top = titleFontMetrics.getHeight(); //Variable para el alto de la fuente
        int bottom = labelFontMetrics.getHeight(); //Variable para abajo de la fuenta
        if (maxValue == minValue) return; //Retorna si son iguales los valores
        /*PARA GRAFICAR LOS RECTANGULAS EN LA GRAFICA*/
        //1.- Haremos una variable tipo double en donde haremos la siguiente operacion
        double scale = (clientHeight - top - bottom) / (maxValue - minValue);
        /*La variable de la anchura interior - la anchura del titulo entre el mayor maximo menos el valor minimo del arreglo*/
        y = clientHeight - labelFontMetrics.getDescent(); //Y = A la anchura interior del canvas - el objeto encapsulado donde se encuenra la fuente
        g.setFont(labelFont); //Asignamos ahora a la fuente esta modificacion
        //Para imprimir los valores del arreglo
        for (int i = 0; i < values.length; i++) {
            int valueX = i * barWidth + 40; //Especificamos donde se encontrara en X con la barra de anchura mas 40 pixeles
            int valueY = 15; //También asignamos en Y donde se va a 
            int height = (int) (values[i] * scale); //Asignamos el tamaño del arreglo por la escala para el canvas
            //Si hay valores en el arreglo entonces al ValuY le vas a sumar
            //El valor maximo menos decremento en el arreglo por la escala del canvas
            if (values[i] >= 0){
                valueY += (int) ((maxValue - values[i]) * scale);
            }
            else {
                //Si no se cumple esa condición asignamos en Y el valor del valor maximo por la escala del canvas
                valueY += (int) (maxValue * scale);
                //Y le asginamos un decremento a lo alto del canvas
                height = -height;
            }
            //Colocamos el color de los rectangulos en grises
            g.setColor(Color.gray);
            //Le pasamos a graficar los valores en X y Y al objeto
            g.fillRect(valueX, valueY, barWidth-2, height);
            //En color blanco el cortorno
            g.setColor(Color.white);
            //Dibujamos en en el canvas el rectangulo
            g.drawRect(valueX, valueY, barWidth-2, height);
            int labelWidth = labelFontMetrics.stringWidth(titulos[i]); //Ahora el labelwidth donde van los nombres
            //El ancho de donde se coloco el titulo le asignamos los titulos que asignamos en el arreglo 
            x = i * barWidth + (barWidth - labelWidth) / 2; //La coordenada en X debera aumentar para seguir pintando mas titulos
            //El valor del for por la barra de anchura + la barra de anchura menos el valor del label de abajo / 2
            g.drawString(titulos[i], x, y); //Dibujamos en este caso otro refresh del titulo
        }
    }
}
