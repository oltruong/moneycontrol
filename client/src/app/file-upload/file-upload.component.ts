import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.sass']
})
export class FileUploadComponent implements OnInit {

  private url: string;

  constructor(private http: HttpClient, private router: Router) {
    this.url = 'http://localhost:8080/rest/bankfileupload';
  }


  ngOnInit() {
  }

  onFileSelected(event) {
    console.log(event);
    const selectedFile = <File> event.target.files[0];
    const fd = new FormData();
    fd.append('operations', selectedFile, selectedFile.name);
    this.http.post(this.url, fd).subscribe(res => {
      console.log(res);
    });

    this.router.navigate(['/operations'], {queryParams: {category: 'empty'}});
  }


}
