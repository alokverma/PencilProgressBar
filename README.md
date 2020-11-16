# PencilProgressBar
Use this pencilLoader for showing custom loader on android dialogs.

# Build

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  Step 2. Add the dependency
  
	dependencies {
	        implementation 'com.github.alokverma:cubic-path:1.1'
	}
  
   # How to use
  
         <com.akki.pencilloader.PencilLoader
              android:id="@+id/pencil"
              android:padding="30dp"
              android:layout_width="match_parent"
              android:layout_height="300dp"
              app:layout_constraintBottom_toBottomOf="parent"
              app:layout_constraintHorizontal_bias="0.243"
              app:layout_constraintLeft_toLeftOf="parent"
              app:layout_constraintRight_toRightOf="parent"
              app:layout_constraintTop_toTopOf="parent"
              app:layout_constraintVertical_bias="0.27"
              app:pencil_loader_stroke="7dp"
              app:pencilloader_color="#000"
              app:pencil_loader_animation_duration="3000"
              />

You can pass loader color , loader animation duration and stroke according to your need.
   
  
 # Demo 
  <p align="center">
  <img src="https://user-images.githubusercontent.com/7018540/99263341-0cf26a00-2845-11eb-94a4-8ad578ad29ad.gif" width="350" title="userprofile">
  </p>

  
