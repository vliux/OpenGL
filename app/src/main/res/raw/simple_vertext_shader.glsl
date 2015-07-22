attribute vec4 a_Position;
attribute vec4 a_Color;

varying vec4 vv_Color;

void main()
{
    vv_Color = a_Color;
    gl_Position = a_Position;
    gl_PointSize = 10.0;
}