package me.bannock.theworld.ui.swing.img;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class GraphicsWrapper extends Graphics2D {

    public GraphicsWrapper(Graphics2D swingComponentGraphics, Graphics2D bufferedImageGraphics){
        this.swingComponentGraphics = swingComponentGraphics;
        this.bufferedImageGraphics = bufferedImageGraphics;
    }

    private final Graphics2D swingComponentGraphics;
    private final Graphics2D bufferedImageGraphics;

    @Override
    public void draw(Shape s) {
        bufferedImageGraphics.draw(s);
        swingComponentGraphics.draw(s);
    }

    @Override
    public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
        swingComponentGraphics.drawImage(img, xform, obs);
        return bufferedImageGraphics.drawImage(img, xform, obs);
    }

    @Override
    public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
        bufferedImageGraphics.drawImage(img, op, x, y);
        swingComponentGraphics.drawImage(img, op, x, y);
    }

    @Override
    public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
        bufferedImageGraphics.drawRenderedImage(img, xform);
        swingComponentGraphics.drawRenderedImage(img, xform);
    }

    @Override
    public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
        bufferedImageGraphics.drawRenderableImage(img, xform);
        swingComponentGraphics.drawRenderableImage(img, xform);
    }

    @Override
    public void drawString(String str, int x, int y) {
        bufferedImageGraphics.drawString(str, x, y);
        swingComponentGraphics.drawString(str, x, y);
    }

    @Override
    public void drawString(String str, float x, float y) {
        bufferedImageGraphics.drawString(str, x, y);
        swingComponentGraphics.drawString(str, x, y);
    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {
        bufferedImageGraphics.drawString(iterator, x, y);
        swingComponentGraphics.drawString(iterator, x, y);
    }

    @Override
    public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
        bufferedImageGraphics.drawImage(img, x, y, observer);
        return swingComponentGraphics.drawImage(img, x, y, observer);
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
        bufferedImageGraphics.drawImage(img, x, y, width, height, observer);
        return swingComponentGraphics.drawImage(img, x, y, width, height, observer);
    }

    @Override
    public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
        bufferedImageGraphics.drawImage(img, x, y, bgcolor, observer);
        return swingComponentGraphics.drawImage(img, x, y, bgcolor, observer);
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
        bufferedImageGraphics.drawImage(img, x, y, width, height, bgcolor, observer);
        return swingComponentGraphics.drawImage(img, x, y, width, height, bgcolor, observer);
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
        bufferedImageGraphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
        return swingComponentGraphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
        bufferedImageGraphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
        return swingComponentGraphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
    }

    @Override
    public void dispose() {
        bufferedImageGraphics.dispose();
        swingComponentGraphics.dispose();
    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, float x, float y) {
        bufferedImageGraphics.drawString(iterator, x, y);
        swingComponentGraphics.drawString(iterator, x, y);
    }

    @Override
    public void drawGlyphVector(GlyphVector g, float x, float y) {
        bufferedImageGraphics.drawGlyphVector(g, x, y);
        swingComponentGraphics.drawGlyphVector(g, x, y);
    }

    @Override
    public void fill(Shape s) {
        bufferedImageGraphics.fill(s);
        swingComponentGraphics.fill(s);
    }

    @Override
    public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
        bufferedImageGraphics.hit(rect, s, onStroke);
        return swingComponentGraphics.hit(rect, s, onStroke);
    }

    @Override
    public GraphicsConfiguration getDeviceConfiguration() {
        return swingComponentGraphics.getDeviceConfiguration();
    }

    @Override
    public void setComposite(Composite comp) {
        bufferedImageGraphics.setComposite(comp);
        swingComponentGraphics.setComposite(comp);
    }

    @Override
    public void setPaint(Paint paint) {
        bufferedImageGraphics.setPaint(paint);
        swingComponentGraphics.setPaint(paint);
    }

    @Override
    public void setStroke(Stroke s) {
        bufferedImageGraphics.setStroke(s);
        swingComponentGraphics.setStroke(s);
    }

    @Override
    public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue) {
        bufferedImageGraphics.setRenderingHint(hintKey, hintValue);
        swingComponentGraphics.setRenderingHint(hintKey, hintValue);
    }

    @Override
    public Object getRenderingHint(RenderingHints.Key hintKey) {
        return swingComponentGraphics.getRenderingHint(hintKey);
    }

    @Override
    public void setRenderingHints(Map<?, ?> hints) {
        bufferedImageGraphics.setRenderingHints(hints);
        swingComponentGraphics.setRenderingHints(hints);
    }

    @Override
    public void addRenderingHints(Map<?, ?> hints) {
        bufferedImageGraphics.addRenderingHints(hints);
        swingComponentGraphics.addRenderingHints(hints);
    }

    @Override
    public RenderingHints getRenderingHints() {
        return swingComponentGraphics.getRenderingHints();
    }

    @Override
    public Graphics create() {
        return new GraphicsWrapper((Graphics2D) swingComponentGraphics.create(), (Graphics2D) bufferedImageGraphics.create());
    }

    @Override
    public void translate(int x, int y) {
        bufferedImageGraphics.translate(x, y);
        swingComponentGraphics.translate(x, y);
    }

    @Override
    public Color getColor() {
        return swingComponentGraphics.getColor();
    }

    @Override
    public void setColor(Color c) {
        bufferedImageGraphics.setColor(c);
        swingComponentGraphics.setColor(c);
    }

    @Override
    public void setPaintMode() {
        bufferedImageGraphics.setPaintMode();
        swingComponentGraphics.setPaintMode();
    }

    @Override
    public void setXORMode(Color c1) {
        bufferedImageGraphics.setXORMode(c1);
        swingComponentGraphics.setXORMode(c1);
    }

    @Override
    public Font getFont() {
        return swingComponentGraphics.getFont();
    }

    @Override
    public void setFont(Font font) {
        bufferedImageGraphics.setFont(font);
        swingComponentGraphics.setFont(font);
    }

    @Override
    public FontMetrics getFontMetrics(Font f) {
        return swingComponentGraphics.getFontMetrics(f);
    }

    @Override
    public Rectangle getClipBounds() {
        return swingComponentGraphics.getClipBounds();
    }

    @Override
    public void clipRect(int x, int y, int width, int height) {
        bufferedImageGraphics.clipRect(x, y, width, height);
        swingComponentGraphics.clipRect(x, y, width, height);
    }

    @Override
    public void setClip(int x, int y, int width, int height) {
        bufferedImageGraphics.setClip(x, y, width, height);
        swingComponentGraphics.setClip(x, y, width, height);
    }

    @Override
    public Shape getClip() {
        return swingComponentGraphics.getClip();
    }

    @Override
    public void setClip(Shape clip) {
        bufferedImageGraphics.setClip(clip);
        swingComponentGraphics.setClip(clip);
    }

    @Override
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
        bufferedImageGraphics.copyArea(x, y, width, height, dx, dy);
        swingComponentGraphics.copyArea(x, y, width, height, dx, dy);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        bufferedImageGraphics.drawLine(x1, y1, x2, y2);
        swingComponentGraphics.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        bufferedImageGraphics.fillRect(x, y, width, height);
        swingComponentGraphics.fillRect(x, y, width, height);
    }

    @Override
    public void clearRect(int x, int y, int width, int height) {
        bufferedImageGraphics.clearRect(x, y, width, height);
        swingComponentGraphics.clearRect(x, y, width, height);
    }

    @Override
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        bufferedImageGraphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
        swingComponentGraphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        bufferedImageGraphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
        swingComponentGraphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        bufferedImageGraphics.drawOval(x, y, width, height);
        swingComponentGraphics.drawOval(x, y, width, height);
    }

    @Override
    public void fillOval(int x, int y, int width, int height) {
        bufferedImageGraphics.fillOval(x, y, width, height);
        swingComponentGraphics.fillOval(x, y, width, height);
    }

    @Override
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        bufferedImageGraphics.drawArc(x, y, width, height, startAngle, arcAngle);
        swingComponentGraphics.drawArc(x, y, width, height, startAngle, arcAngle);
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        bufferedImageGraphics.fillArc(x, y, width, height, startAngle, arcAngle);
        swingComponentGraphics.fillArc(x, y, width, height, startAngle, arcAngle);
    }

    @Override
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
        bufferedImageGraphics.drawPolyline(xPoints, yPoints, nPoints);
        swingComponentGraphics.drawPolyline(xPoints, yPoints, nPoints);
    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        bufferedImageGraphics.drawPolygon(xPoints, yPoints, nPoints);
        swingComponentGraphics.drawPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        bufferedImageGraphics.fillPolygon(xPoints, yPoints, nPoints);
        swingComponentGraphics.fillPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public void translate(double tx, double ty) {
        bufferedImageGraphics.translate(tx, ty);
        swingComponentGraphics.translate(tx, ty);
    }

    @Override
    public void rotate(double theta) {
        bufferedImageGraphics.rotate(theta);
        swingComponentGraphics.rotate(theta);
    }

    @Override
    public void rotate(double theta, double x, double y) {
        bufferedImageGraphics.rotate(theta, x, y);
        swingComponentGraphics.rotate(theta, x, y);
    }

    @Override
    public void scale(double sx, double sy) {
        bufferedImageGraphics.scale(sx, sy);
        swingComponentGraphics.scale(sx, sy);
    }

    @Override
    public void shear(double shx, double shy) {
        bufferedImageGraphics.shear(shx, shy);
        swingComponentGraphics.shear(shx, shy);
    }

    @Override
    public void transform(AffineTransform Tx) {
        bufferedImageGraphics.transform(Tx);
        swingComponentGraphics.transform(Tx);
    }

    @Override
    public void setTransform(AffineTransform Tx) {
        bufferedImageGraphics.setTransform(Tx);
        swingComponentGraphics.setTransform(Tx);
    }

    @Override
    public AffineTransform getTransform() {
        return swingComponentGraphics.getTransform();
    }

    @Override
    public Paint getPaint() {
        return swingComponentGraphics.getPaint();
    }

    @Override
    public Composite getComposite() {
        return swingComponentGraphics.getComposite();
    }

    @Override
    public void setBackground(Color color) {
        bufferedImageGraphics.setBackground(color);
        swingComponentGraphics.setBackground(color);
    }

    @Override
    public Color getBackground() {
        return swingComponentGraphics.getBackground();
    }

    @Override
    public Stroke getStroke() {
        return swingComponentGraphics.getStroke();
    }

    @Override
    public void clip(Shape s) {
        bufferedImageGraphics.clip(s);
        swingComponentGraphics.clip(s);
    }

    @Override
    public FontRenderContext getFontRenderContext() {
        return swingComponentGraphics.getFontRenderContext();
    }

}
