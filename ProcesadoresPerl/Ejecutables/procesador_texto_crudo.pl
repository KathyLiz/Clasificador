use strict;
use warnings;
use UTF8;

print "Ingrese el nombre del archivo\n";
  
my $filename = <STDIN>;
#diccionarios 
my $archivoc = 'conjunciones.txt';
my $conjunciones = abrir ($archivoc);

my $archivoa = 'adjetivosp.txt';
my $adjetivos = abrir ($archivoa);

my $archivoan = 'adjetivosn.txt';
my $adjetivosnegativos = abrir ($archivoan);
#archivo de texto
my $contenido = abrir ($filename);

my $resultado;
my $contadornegativo=0;
my $contador=0;
my $conj;
my $arffile = "\@relation polaridad\n\@attribute positiveadjetive numeric\n\@attribute negativeadjetive numeric\n\@attribute punctuation numeric\n\@attribute class {positive,negative,neutral}\n\@data\n";
my $conjuncionBool;

#Bloque de expresiones regulares para limpieza de datos 
$contenido =~ s/\n/ /g;
$contenido =~ s/(?:(?!\*\*\*).)*\*\*\*//s;
$contenido =~ s/([^\.]{4,})\./$1\.\n/g;
$contenido =~ s/[\.\"\;\,\:\-\'\*\@\[\]\{\}\?\!\#\$\/\\\>\<\(\)\~\=)]//g;
#Se alamacenan las oraciones  en un archivo para verificar la separación correcta
guardar("texto.txt",$contenido);
#se lee cada oración del archiv
while( $contenido =~ /([^\n]+)/g){
	
	#Se establecen contadores para las evaluaciones 
	$contadornegativo=0;
	$contador=0;
	my $oracion = $1;
	$conjuncionBool=2;
	#La variable $conjuncionBool puede tomar 1=si tiene conjunciones o 2= no tiene conjunciones
	while($conjunciones=~/([a-z]+)/g){
		$conj = $1;
		#Se elimina la oración que está antes de la conjunción
		if($oracion=~/$conj([^\n]+)/gi){
			
			$oracion=$1;
			$conjuncionBool=1;
		}
	}
	
	#Se toma la oración que ha sobrado en caso de q haya conjunción  de lo contrario la oración está completa
	#Se separa las oraciones en palabras
	while( $oracion =~ /([^ ]+)/g){
		
		my $palabra = $1;
		#Se evalúa si la palabra de la oración es un adjetivo positivo
		if ($adjetivos=~/\b$palabra\b/){
			$contador++;
		}
		#Se evalúa si la palabra de la oración es un adjetivo negativo
		elsif ($adjetivosnegativos=~/\b$palabra\b/){
			$contadornegativo++;
		}
	}
	#Función que evalúa si la oración es positiva o negativa y adjunta el resultado 
	$resultado = evaluador($contador,$contadornegativo,$conjuncionBool);

	$arffile=$arffile."$contador,$contadornegativo,$conjuncionBool,$resultado\n";

}

guardar("libro.arff", $arffile);


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

	
	if ($adjPositivos-$adjNegativos>0 ){
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