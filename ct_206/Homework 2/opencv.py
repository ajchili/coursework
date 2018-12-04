import numpy as np
import cv2


mat = np.zeros((256, 256, 1), dtype = "uint8")
cv2.imshow('window', mat)
cv2.waitKey(0)