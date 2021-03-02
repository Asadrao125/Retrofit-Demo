package com.gexton.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gexton.retrofitdemo.model.Comment;
import com.github.barteksc.pdfviewer.PDFView;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class GeneratePdfActivity extends AppCompatActivity {
    private File pdfFile;
    Button btnGenerate;
    ArrayList<Comment> list = new ArrayList<>();
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_pdf);

        btnGenerate = findViewById(R.id.btnGenerate);
        pdfView = findViewById(R.id.pdfView);
        setTitle("Generate PDF");

        list.add(new Comment(1, 1, "Name1", "asad@gmail.com", "commnet1"));
        list.add(new Comment(2, 2, "Name2", "asad@gmail.com", "commnet2"));
        list.add(new Comment(3, 3, "Name3", "asad@gmail.com", "commnet3"));
        list.add(new Comment(4, 4, "Name4", "asad@gmail.com", "commnet4"));
        list.add(new Comment(5, 5, "Name5", "asad@gmail.com", "commnet5"));
        list.add(new Comment(6, 6, "Name6", "asad@gmail.com", "commnet6"));
        list.add(new Comment(7, 7, "Name7", "asad@gmail.com", "commnet7"));
        list.add(new Comment(8, 8, "Name8", "asad@gmail.com", "commnet8"));
        list.add(new Comment(9, 9, "Name9", "asad@gmail.com", "commnet9"));
        list.add(new Comment(10, 10, "Name10", "asad@gmail.com", "commnet10"));
        list.add(new Comment(11, 11, "Name11", "asad@gmail.com", "commnet11"));
        list.add(new Comment(12, 12, "Name12", "asad@gmail.com", "commnet12"));
        list.add(new Comment(13, 13, "Name13", "asad@gmail.com", "commnet13"));
        list.add(new Comment(14, 14, "Name14", "asad@gmail.com", "commnet14"));
        list.add(new Comment(15, 15, "Name15", "asad@gmail.com", "commnet15"));
        list.add(new Comment(16, 16, "Name16", "asad@gmail.com", "commnet16"));
        list.add(new Comment(17, 17, "Name17", "asad@gmail.com", "commnet17"));
        list.add(new Comment(18, 18, "Name18", "asad@gmail.com", "commnet18"));

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createPDF(list);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void createPDF(List<Comment> list) throws FileNotFoundException, DocumentException {
        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Retrofit-Demo");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
        }
        String pdfName = "retrofit-demo.pdf";
        pdfFile = new File(docsFolder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);
        Document document = new Document(PageSize.A4);
        PdfPTable table = new PdfPTable(new float[]{3, 3, 3, 3, 3});
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setFixedHeight(50);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setWidthPercentage(100);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell("Post Id");
        table.addCell("Id");
        table.addCell("Name");
        table.addCell("Email");
        table.addCell("Comment");
        table.setHeaderRows(1);
        PdfPCell[] cell = table.getRow(0).getCells();
        for (int i = 0; i < 5; i++) {
            cell[i].setBackgroundColor(BaseColor.LIGHT_GRAY);
        }

        for (int j = 0; j < list.size(); j++) {
            table.addCell(String.valueOf(list.get(j).id));
            table.addCell(String.valueOf(list.get(j).postID));
            table.addCell(list.get(j).name);
            table.addCell(list.get(j).email);
            table.addCell(list.get(j).text);
        }

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 30.0f, Font.UNDERLINE, BaseColor.BLUE);
            Font g = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.NORMAL, BaseColor.BLUE);
            try {
                InputStream ims = getAssets().open("logo.png");
                Bitmap bmp = BitmapFactory.decodeStream(ims);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                Image image = Image.getInstance(stream.toByteArray());
                document.add(image);
            } catch (IOException ex) {
                return;
            }

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Retrofit Demo\n\n", f));
            document.add(table);
            document.close();
            openPdf();

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void previewPDF() {
        PackageManager packageManager = getApplicationContext().getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        Toast.makeText(this, "" + pdfFile, Toast.LENGTH_SHORT).show();
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getApplicationContext().getPackageName() + ".provider", pdfFile);
            intent.setDataAndType(photoURI, "application/pdf");
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please Download Pdf Viewer", Toast.LENGTH_SHORT).show();
        }
    }

    private void openPdf() {
        pdfView.fromFile(pdfFile)/*.enableSwipe(false).swipeHorizontal(true)*/.enableDoubletap(true).load();
    }

}