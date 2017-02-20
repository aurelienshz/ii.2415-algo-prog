var gulp = require('gulp');
var exec = require('child_process').exec;

gulp.task('launch', function () {
  exec('make pdf launch', function (error, stdout, stderr) {
    if (error) {
      console.error('Make launch target error: ' + error);
      return;
    }
    if (stdout) {
      console.log('Make launch target stdout: ' + stdout);
    }
    if (stderr) {
      console.log('Make launch target stderr: ' + stderr);
    }
  });
});

gulp.task('make', function () {
  exec('make', function (error, stdout, stderr) {
    if (error) {
      console.error('Make error: ' + error);
      return;
    }
    if (stdout) {
      console.log('Make stdout: ' + stdout);
    }
    if (stderr) {
      console.log('Make stderr: ' + stderr);
    }
  });
});

gulp.task('watch', function() {
  gulp.watch('./**/*.tex', ['make']);
});

gulp.task('default', ['launch', 'watch']);
