function addOnRotateListener() {

  if ("addEventListener" in window) {
    window.addEventListener("resize", function () {

      if (window.innerHeight > window.innerWidth) {
        // portrait
        window.scrollTo(0, 0);
      } else {
        // landscape
        var barcodeDiv = document.getElementById("iata-barcode-container");
        barcodeDiv.scrollIntoView(true);
      }

    });
  }

}
