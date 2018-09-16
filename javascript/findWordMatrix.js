/*
*    Return true if a word can be build with consecutive characters in a matrix 
*    a character in the matrix must be used just one time.
*    
*    matrix: 
*    [ x , o , l , b ]
*    [ s , d , a , d ]
*    [ a , r , r , z ]
*
*    word: solar
*    return: true
*    The word can be created because the 's' char is connected with the 'o' char, which is also
*    connected with 'l'...
*/

(function() {

    /* Matrix */
    var a = [ [ "x", "o", "l", "b" ], [ "s", "d", "a", "d" ], [ "a", "r", "r", "z"] ];

    /* Word to look up for */
    var myString = "sol";

    /* Char array */
    var arrString = myString.split('');

    /* Create a clone of the matrix to handle cell visits */
    var b = a.map(function(element){ 
      let el = element.slice(0);
      return el;
    });
  
    console.log(a);
  
    /* Initialize the clone with 0 (cell still not visited) */
    for(let x = 0; x < b.length; x++) {
      for(let y = 0; y < b[x].length; y++) {
          b[ x ][ y ] = 0;
      }
    }
  
    /* Look up for the starting char */
    var strPosition = 0;
    var found = false;
    for(let x = 0; x < a.length; x++) {
      for(let y = 0; y < a[x].length; y++) {
          if( a[ x ][ y ] === arrString[ strPosition ] ) {
              found = traverse(x, y, 0, "");
              if( found ) {
                  break;
              }
          }
      }
    }
  
    console.log("Found? " + found);
  
    /* Recursive function to traverse the matrix looking for the right chars */
    function traverse(x, y, strPosition, carry) {
  
      if( arrString[ strPosition ] === a[ x ][ y ] ) {
        carry += a[ x ][ y ];
        b[ x ][ y ] = 1;
  
        if(carry === myString) {
          return true;
        }
  
        let res = false;
        strPosition++;
        for(let z = x - 1; z <= x + 1; z++) {
          for(let w = y - 1; w <= y + 1; w++) {
              if(z >= 0 && z < a.length & w >= 0 && w < a[0].length) {
                if( b[ z ][ w ] === 0 ) {
                  res = traverse(z, w, strPosition, carry);
                  if( res ) {
                    return true;
                  }
                }
              }
          }
        }
      }
  
      return false;
    }
  
  })();
  
  
  
