const fileInput = document.querySelector("[name='file']");

const showUploadImages = (arr) => {
  const output = document.querySelector(".uploadResult ul");

  console.log("---- arr");
  console.log(arr);

  let tags = "";
  arr.forEach((element, idx) => {
    tags += `<li data-name="${element.fileName}" data-path="${element.folderPath}" data-uuid="${element.uuid}">`;
    tags += `<img src="/upload/display?fileName=${element.thumbnailURL}">`;
    tags += `<a href="${element.imageURL}"> <i class="fa-solid fa-circle-xmark"></i> </a>`;
    tags += "</li>";
  });
  output.insertAdjacentHTML("beforeend", tags);
};

// a href 가져오기 : 파일명 가져오기

document.querySelector(".uploadResult").addEventListener("click", (e) => {
  // a (X) 클릭 시 a 기본 태그 중지
  e.preventDefault();

  // 이벤트 대상
  console.log("e.target ", e.target);
  // 이벤트 대상의 부모
  console.log("e.currentTarget ", e.currentTarget);

  // 이벤트 대상과 제일 가까운 : closest(selector)
  const aTag = e.target.closest("a");
  const liTag = e.target.closest("li");

  // 속성 접근 : . or getAttribute("속성명")
  // img.src : img의 src값 가져오기

  const fileName = aTag.getAttribute("href");
  let form = new FormData();
  form.append("fileName", fileName);

  if (!confirm("정말로 삭제하시겠습니까?")) return;

  axios
    .post(`/upload/removeFile`, form, {
      headers: {
        "X-CSRF-TOKEN": csrf,
      },
    })
    .then((res) => {
      console.log(res.data);

      if (res.data) {
        liTag.remove();
      }
    });
});

fileInput.addEventListener("change", (e) => {
  // 버튼 클릭 시 uploadFiles 가져오기
  const inputFile = e.target;
  const files = inputFile.files;
  console.log(files);

  // form 생성 후 업로드된 파일 uppend
  let form = new FormData();

  for (let i = 0; i < files.length; i++) {
    form.append("uploadFiles", files[i]);
  }

  axios
    .post(`/upload/files`, form, {
      headers: {
        "X-CSRF-TOKEN": csrf,
      },
    })
    .then((res) => {
      console.log(res.data);
      showUploadImages(res.data);
    });
});
