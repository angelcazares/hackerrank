/*
There are  hourglasses in , and an hourglass sum is the sum of an hourglass' values. 
Calculate the hourglass sum for every hourglass in , then print the maximum hourglass sum.
Complete the function hourglassSum in the editor below. It should return an integer, the maximum hourglass sum in the array.
*/
(function () {

    // Complete the hourglassSum function below.
    const hackerrank = window.hackerrank || {};
    hackerrank.arrays = hackerrank.arrays || {};
    hackerrank.arrays.hourglassSum = {
        run: function (arr) {
            var val = null;
            var tmp = 0;
            for (var idy = 0; idy <= arr.length / 2; idy++) {
                for (var idx = 0; idx + 3 <= arr.length; idx++) {
                    tmp = arr[idy][idx] + arr[idy][idx + 1] + arr[idy][idx + 2];
                    tmp += arr[idy + 1][idx + 1];
                    tmp += arr[idy + 2][idx] + arr[idy + 2][idx + 1] + arr[idy + 2][idx + 2];
                    val = val !== null ? (val > tmp ? val : tmp) : tmp;
                }
            }
            return val;
        }
    }

    /* Test cases */
    var test1 = [[1, 1, 1, 0, 0, 0],
                [0, 1, 0, 0, 0, 0],
                [1, 1, 1, 0, 0, 0],
                [0, 0, 2, 4, 4, 0],
                [0, 0, 0, 2, 0, 0],
                [0, 0, 1, 2, 4, 0]];

    var testR = hackerrank.arrays.hourglassSum.run(test1);
    console.log( "Result: " + testR);
    /* Expected: 19 */

})();