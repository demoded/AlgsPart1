	public void draw(){
		for (int i = 1; i <= this.N; i++) 
			for (int j = 1; j <= this.N; j++) {
				if (this.isFull(i, j)){
					StdDraw.setPenColor(new Color(127, 127, 127));
					StdDraw.filledRectangle(i*0.09, 1 - j*0.09, 0.042, 0.042);
				}
				else {
					StdDraw.setPenColor(new Color(255, 255, 255));
					StdDraw.filledRectangle(i*0.09, 1 - j*0.09, 0.042, 0.042); 
				}
				
			}
		
	}
