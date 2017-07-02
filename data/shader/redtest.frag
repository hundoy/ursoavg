#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

uniform sampler2D u_texture;
uniform vec2 resolution;

varying LOWP vec4 v_color;
varying vec2 v_texCoord;

void main(){
    vec4 texColor = texture2D(u_texture, v_texCoord);

    vec2 position = (gl_FragCoord.xy / resolution.xy)-vec2(0.5);

    float len = length(position);

    gl_FragColor = vec4(vec3(len),1.0);
//    gl_FragColor = texColor * v_color;
}