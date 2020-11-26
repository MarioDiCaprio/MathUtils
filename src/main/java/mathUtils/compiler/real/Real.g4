grammar Real;

@header {
import java.util.List;
import java.util.LinkedList;

import mathUtils.calculus.Function;
import mathUtils.calculus.Polynomial;
import mathUtils.linear.Point;
}


parseNumber returns [double d]:
    a=addition {$d = $a.f.y(0);}
;


parseFunction returns [Function f]:
    a=addition {$f = $a.f;}
;


addition returns [Function f]:
    f1 = multiplication             {$f = x -> $f1.f.y(x);}
         (
         '+' f2 = multiplication
         {
            Function df = $f;
            $f = x -> df.y(x) + $f2.f.y(x);
         }
         |
         '-' f2 = multiplication
         {
             Function df = $f;
             $f = x -> df.y(x) - $f2.f.y(x);
         }
         )*
;


multiplication returns [Function f]:
    f1 = function               {$f = x -> $f1.f.y(x);}
         (
         '*' f2 = function
         {
             Function df = $f;
             $f = x -> df.y(x) * $f2.f.y(x);
         }
         |
         '/' f2 = function
         {
              Function df = $f;
              $f = x -> df.y(x) / $f2.f.y(x);
         }
         )*
;


function returns [Function f]:
     x = atom { $f = $x.f; }
     |
     'sin' a=function   { $f = x -> Math.sin($a.f.y(x)); }
     |
     'cos' a=function   { $f = x -> Math.cos($a.f.y(x)); }
     |
     'tan' a=function   { $f = x -> Math.tan($a.f.y(x)); }
     |
     'sqrt' a=function  { $f = x -> Math.sqrt($a.f.y(x)); }
     |
     'log' a=function  { $f = x -> Math.log($a.f.y(x)) / Math.log(10); }
     |
     'ln' a=function  { $f = x -> Math.log($a.f.y(x)); }
     |
     'd|dx' a=function  { $f = $a.f.derivative(); }
     |
     'int' a=function  { $f = $a.f.integral(); }
     |
     x=atom '^' a=function           { $f = x -> Math.pow($x.f.y(x), $a.f.y(x)); }
     |
     'fromPoints'
     LEFT       { List<Point> list = new LinkedList<>(); }
     (p=point   { list.add($p.p); })+
     RIGHT      { $f = Polynomial.fromPoints( list.toArray(new Point[list.size()]) ).toFunction(); }
;


atom returns [Function f]:
    t=term          {$f = $t.f;}
    |
    LEFT
    exp = addition      {$f = $exp.f;}
    RIGHT
;


term returns [Function f]:
    n=NUMBER        {$f = x -> Double.parseDouble($n.text);}
    |
    'x'             {$f = x -> x;}
    |
    '-' t=term      {$f = x -> -$t.f.y(x);}
    |
    '+' t=term      {$f = x -> $t.f.y(x);}
;


//////////////////////////////////////////////////////////////////////////////////


point returns [Point p]:
LEFT x=NUMBER '|' y=NUMBER RIGHT
{
    double dx = Double.parseDouble($x.text);
    double dy = Double.parseDouble($y.text);
    $p = new Point(dx, dy);
}
;


//////////////////////////////////////////////////////////////////////////////////

NUMBER: ('0'..'9') + ('.' ('0'..'9')+)?;

LEFT:   '(';
RIGHT:  ')';

WS: [ \n\f\r\t]+ -> skip;