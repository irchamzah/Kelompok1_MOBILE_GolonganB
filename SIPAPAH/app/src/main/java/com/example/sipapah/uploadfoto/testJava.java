package com.example.sipapah.uploadfoto;
//
//import android.Manifest;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Build;
//import android.provider.MediaStore;
//import android.util.Log;
//import android.widget.ImageView;
//
//import androidx.core.content.FileProvider;
//
//import com.example.sipapah.activity.LayananActivity;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import static android.app.Activity.RESULT_OK;
//
public class testJava {

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        easyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
//            @Override
//            public void onMediaFilesPicked(MediaFile[] imageFiles, MediaSource source) {
//                onPhotosReturned(imageFiles);
//            }
//
//            @Override
//            public void onImagePickerError(@NonNull Throwable error, @NonNull MediaSource source) {
//                //Some error handling
//                error.printStackTrace();
//            }
//
//            @Override
//            public void onCanceled(@NonNull MediaSource source) {
//                //Not necessary to remove any files manually anymore
//            }
//        });
//    }



//
//    String pathToFile;
//    ImageView imageView;
//
//
//    if (Build.VERSION.SDK_INT >= 23) {
//        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
//    }
//
//
//    private void dispatchPictureTakerAction(){
//        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if(takePic.resolveActivity(getPackageManager()) != null) {
//            File photofile = null;
//            photofile = createPhotoFile();
//
//            if (photofile != null) {
//                String pathToFile = photofile.getAbsolutePath();
//                Uri photoURI = FileProvider.getUriForFile(LayananActivity.this, "irchamzah", photofile);
//                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePic, 1);
//            }
//
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == 1) {
//                Bitmap bitmap = BitmapFactory.decodeFile(PathToFile);
//                imageView.setImageBitmap(bitmap);
//            }
//        }
//    }
//
//    private File createPhotoFile(){
//        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        File storageDir = getExternalStoragePublicDirectory(Environtment.DIRECTORY_PICTURES);
//        File image = null;
//        try {
//            image = File.createTempFile(name,".jpg", storageDir);
//        } catch (IOException e) {
//            Log.d("mylog", "Excep : " + e.toString());
//        }
//        return image;
//    }
//
}
