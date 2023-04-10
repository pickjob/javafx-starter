/**
 * javafx.scene.transform.Transform: JavaFx 内置变换
 *      javafx.scene.transform.Translate: 移动变换
 *          [   1   0   0   x   ]
 *          [   0   1   0   y   ]
 *          [   0   0   1   z   ]
 *      javafx.scene.transform.Rotate: 旋转变换
 *          [   cos(t)   -sin(t)   0   x-x*cos(t)+y*sin(t)   ]
 *          [   sin(t)    cos(t)   0   y-x*sin(t)-y*cos(t)   ]
 *          [     0         0      1           z             ]
 *      javafx.scene.transform.Scale: 放大、缩小变换
 *          [   x   0   0   (1-x)*pivotX   ]
 *          [   0   y   0   (1-y)*pivotY   ]
 *          [   0   0   z   (1-z)*pivotZ   ]
 *      javafx.scene.transform.Shear: 剪切
 *          [   1   x   0   -x*pivotY   ]
 *          [   y   1   0   -y*pivotX   ]
 *          [   0   0   1       0       ]
 *      javafx.scene.transform.Affine: 仿射变换
 *          [ x']   [  mxx  mxy  mxz  tx  ] [ x ]   [ mxx * x + mxy * y + mxz * z + tx ]
 *          [ y'] = [  myx  myy  myz  ty  ] [ y ] = [ myx * x + myy * y + myz * z + ty ]
 *          [ z']   [  mzx  mzy  mzz  tz  ] [ z ]   [ mzx * x + mzy * y + mzz * z + tz ]
 *          [ 1 ]
 */