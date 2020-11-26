grammar Complex;

@header {
import mathUtils.calculus.complex.Imaginary;
import mathUtils.calculus.complex.Complex;
import mathUtils.calculus.complex.ComplexMath;
import mathUtils.calculus.complex.ComplexFunction;
}


parseNumber returns [Number n]:
    a=addition  {$n = $a.f.z(0);}
;

parseFunction returns [ComplexFunction f]:
    a=addition  {$f = $a.f;}
;


addition returns [ComplexFunction  f]:
    m1 = multiplication             {$f = z ->   $m1.f.z(z);}
         (
         PLUS m2 = multiplication
         {
             ComplexFunction df = $f;
             $f = z -> ComplexMath.add( df.z(z), $m2.f.z(z) );
         }
         |
         MINUS m2 = multiplication
         {
              ComplexFunction df = $f;
              $f = z -> ComplexMath.subtract( df.z(z), $m2.f.z(z) );
         }
         )*
;


multiplication returns [ComplexFunction f]:
    f1 = power               {$f = z ->   $f1.f.z(z);}
         (
         TIMES f2 = power
         {
            ComplexFunction df = $f;
            $f = z -> ComplexMath.multiply( df.z(z), $f2.f.z(z) );
         }
         |
         DIVIDE f2 = power
         {
               ComplexFunction df = $f;
               $f = z -> ComplexMath.divide( df.z(z), $f2.f.z(z) );
         }
         )*
;


power returns [ComplexFunction f]:
    f1 = function               {$f = z ->   $f1.f.z(z);}
         (
         '^' f2 = function
         {
                ComplexFunction df = $f;
                $f = z -> ComplexMath.pow( df.z(z), $f2.f.z(z) );
         }
         )*
;


function returns [ComplexFunction f]:
     x = atom { $f = $x.f; }
     |
     'sin' a=function   { $f = z ->  ComplexMath.sin($a.f.z(z)); }
     |
     'cos' a=function   { $f = z ->  ComplexMath.cos($a.f.z(z)); }
     |
     'tan' a=function   { $f = z ->  ComplexMath.tan($a.f.z(z)); }
     |
     'cis' a=function   { $f = z ->  ComplexMath.cis($a.f.z(z)); }
     |
     'sqrt' a=function  { $f = z ->  ComplexMath.sqrt($a.f.z(z)); }
     |
     'log' a=function  { $f = z ->  ComplexMath.log($a.f.z(z), 10); }
     |
     'ln' a=function  { $f = z ->  ComplexMath.ln($a.f.z(z)); }
     |
     'sqrt' a=function  { $f = z ->  ComplexMath.sqrt($a.f.z(z)); }
     |
     'Re' a=function  { $f = z ->  new Complex($a.f.z(z)).real; }
     |
     'Im' a=function  { $f = z ->  new Complex($a.f.z(z)).imaginary; }
;


atom returns [ComplexFunction f]:
    t=term          {$f = $t.f;}
    |
    LEFT
    exp = addition          {$f = z ->  $exp.f.z(z);}
    RIGHT
;


term returns [ComplexFunction f]:
        'z'                         {$f = z -> z;}
        |
        (
            PLUS n = NUMBER         {$f = z -> Double.parseDouble($n.text);}
            |
            MINUS n = NUMBER        {$f = z -> -Double.parseDouble($n.text);}
        )
        |
        n=NUMBER                    {$f = z ->  Double.parseDouble($n.text);}
        |
        (
            PLUS n = NUMBER 'i'     {$f = z ->  new Imaginary( Double.parseDouble($n.text) );}
            |
            MINUS n = NUMBER 'i'    {$f = z ->  new Imaginary( -Double.parseDouble($n.text) );}
        )
        |
        n=NUMBER 'i'                {$f = z ->  new Imaginary( Double.parseDouble($n.text) );}
        |
        (
            PLUS     LEFT
                     exp = addition          {$f = z ->  $exp.f.z(z);}
                     RIGHT
            |
            MINUS   LEFT
                    exp = addition          {$f = z ->  ComplexMath.subtract(0, $exp.f.z(z));}
                    RIGHT
        )
        |
        'i' {$f = z -> Imaginary.I;}
        |
        'e' {$f = z -> Math.E;}
        |
        'pi' {$f = z -> Math.PI;}
;


//////////////////////////////////////////////////////////////////////////////////

NUMBER: ('0'..'9') + ('.' ('0'..'9')+)?;

PLUS:   '+';
MINUS:  '-';
TIMES:  '*';
DIVIDE: '/';
POWER:  '^';
LEFT:   '(';
RIGHT:  ')';
COMA:   ',';


WS: [ \n\f\r\t]+ -> skip;