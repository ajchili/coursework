import os
import shutil
import time

lorem_ipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent imperdiet sed felis eget pharetra. Proin suscipit leo quis nulla consequat fringilla. Cras id feugiat libero. Suspendisse sed aliquam est, aliquam laoreet justo. In in nibh convallis, egestas enim eu, bibendum nulla. Mauris massa lacus, mattis congue ligula ac, suscipit tempor nibh. Curabitur scelerisque nisl vel maximus varius. Quisque accumsan ornare rutrum. Morbi in commodo mauris. Donec eu risus est. Sed lorem dolor, imperdiet nec velit vel, faucibus interdum erat. Fusce malesuada vel felis quis auctor.\n\nNunc sit amet nibh odio. Morbi sollicitudin consectetur nisi sit amet tempus. Mauris consequat purus vitae turpis porta, sit amet pellentesque felis laoreet. Proin rutrum rhoncus turpis, commodo semper tortor ultricies at. Sed id auctor sapien. Proin vel justo id massa suscipit gravida. Aenean mattis, nisi id pulvinar consequat, ipsum metus vestibulum arcu, eget accumsan lectus nunc eget metus. Sed at elit felis. Nam efficitur, odio ut scelerisque mattis, justo erat vehicula felis, id finibus libero nibh et nibh. Proin sit amet ante vel urna condimentum vehicula.\n\nUt ut libero facilisis, maximus dolor vel, dapibus turpis. Nulla facilisi. Cras sed euismod magna, sit amet suscipit quam. Quisque sed ultrices mi, non tempor libero. Ut quam nisi, pellentesque quis sagittis sit amet, eleifend volutpat diam. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec in ullamcorper sem, at mollis eros. Praesent lacus elit, faucibus lobortis sollicitudin id, porta vitae nibh. Donec placerat lacus quam, eget auctor metus lobortis ullamcorper.\n\nNulla pellentesque massa nisl, vitae interdum dui pulvinar ac. Vestibulum eu nunc vulputate, auctor ante sed, consectetur diam. Morbi venenatis congue auctor. Vivamus lobortis ante vel tortor mattis laoreet. Nunc neque sapien, porttitor vitae ligula in, volutpat aliquam dolor. Nullam enim augue, porta et sodales eget, posuere sit amet lacus. Sed condimentum ornare elit sit amet imperdiet. Morbi nec nunc arcu. Nulla luctus libero vel velit elementum ultrices. Sed sit amet ante sed enim mollis aliquet non in massa.\n\nVestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Phasellus ultrices eros fermentum varius eleifend. Donec lacinia facilisis arcu at euismod. Nullam non massa sed sem tempor rutrum ac eget eros. Integer pharetra quam efficitur orci dictum, quis convallis enim tristique. Praesent vel lectus gravida, pulvinar quam et, fringilla erat. Nunc sed placerat magna, sed pharetra velit. Nunc sodales magna pretium, imperdiet tortor sed, scelerisque nisl. Etiam vitae faucibus magna, id dignissim ante. Sed vel felis sed sapien pharetra ultricies ut quis lorem. Etiam in varius felis. Vivamus non euismod purus, vel porta magna. Suspendisse euismod convallis justo, ut fermentum diam commodo eget. Nam euismod turpis ut aliquet venenatis. Aliquam vel elit diam. Fusce efficitur dui ut massa tristique dapibus."


def create_directory(directory):
  try:
    os.makedirs(directory)
  except OSError:
    print("Unable to create directory:", directory)


def create_file(directory, file_name, file_data):
  f = open("%s/%s" % (directory, file_name), "w+")
  f.write(file_data)
  f.close()


def delete_directory(directory):
  try:
    os.rmdir(directory)
  except OSError:
    print("Unable to delete directory:", directory)
    shutil.rmtree(directory)
    print("Attempting to delete directory as a tree.")


directory = "./tmp"
create_directory(directory)
for i in range(0, 5):
  create_file(directory, "%i.txt" % i, lorem_ipsum)
time.sleep(5)
delete_directory(directory)