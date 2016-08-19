use strict;
use warnings;
use UTF8;

print "Ingrese el nombre del archivo\n";
  
my $filename = <STDIN>;

my $archivoc = 'conjunciones.txt';
my $conjunciones = abrir ($archivoc);

my $archivoa = 'adjetivosp.txt';
my $adjetivos = abrir ($archivoa);

my $archivoan = 'adjetivosn.txt';
my $adjetivosnegativos = abrir ($archivoan);

my $contenido = abrir ($filename);

my $resultado;
my $contadornegativo=0;
my $contador=0;
my $conj;
my $arffile = "\@relation polaridad\n\@attribute positiveadjetive numeric\n\@attribute negativeadjetive numeric\n\@attribute conjunction numeric\n\@attribute class {positive,negative,neutral}\n\@data\n";
my $conjuncionBool;


$contenido =~ s/\n/ /g;
$contenido =~ s/(?:(?!\*\*\*).)*\*\*\*//s;
$contenido =~ s/([^\.]{4,})\./$1\.\n/g;
$contenido =~ s/[\.\"\;\,\:\-\'\*\@\[\]\{\}\?\!\#\$\/\\\>\<\(\)\~]//g;

guardar("texto.txt",$contenido);

while( $contenido =~ /([^\n]+)/g){
	
	$contadornegativo=0;
	$contador=0;
	my $oracion = $1;
	$conjuncionBool=2;

	while($conjunciones=~/([a-z]+)/g){
		$conj = $1;
		
		if($oracion=~/$conj([^\n]+)/gi){
			
			$oracion=$1;
			$conjuncionBool=1;
		}
	}
	

	while( $oracion =~ /([^ ]+)/g){
		
		my $palabra = $1;
		if ($adjetivos=~/\b$palabra\b/){
			$contador++;
		}
		elsif ($adjetivosnegativos=~/\b$palabra\b/){
			$contadornegativo++;
		}
	}
	#$resultado = evaluador($contador,$contadornegativo,$conjuncionBool);

	$arffile=$arffile."$contador,$contadornegativo,$conjuncionBool,?\n";

}

guardar("libro1.arff", $arffile);


sub abrir {
	my ($nombre_archivo)= @_;
	local $/;
	open (FILE, $nombre_archivo) or die "No se puede leer el archivo \"$nombre_archivo\" [$!]\n";
	my $contenido = <FILE>;
	close (FILE);

	return $contenido;
}

sub guardar {
	my ($nombre_archivo, $contenido)= @_;
	open (FILE, ">$nombre_archivo") or die "No se puede guardar el archivo \"nombre_archivo\" [$!]\n";
	print FILE $contenido;
	close (FILE);
	print "Archivo guardado exitosamente\n";
}

sub evaluador{

	my ($adjPositivos,$adjNegativos,$conjuncionBool)=@_;

	
	if ($adjPositivos-$adjNegativos>0){
		return "positive";
	}

	elsif ($adjPositivos-$adjNegativos<0){
		return "negative";
	}

	elsif ($adjPositivos-$adjNegativos==0){
		return "neutral";
	}
	else{
		return"$adjPositivos,$adjNegativos,$conjuncionBool,error";
	}
}