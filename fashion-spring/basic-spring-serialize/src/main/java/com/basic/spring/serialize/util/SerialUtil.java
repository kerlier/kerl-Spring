package com.basic.spring.serialize.util;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class SerialUtil {

    public static  <T> byte[] jdkSerialToByteArray(T t){
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);
            objectOutputStream.flush();
            objectOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static <T> byte[] hessianSerialToByteArray(T t){
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();){
            HessianOutput hessianOutput = new HessianOutput(byteArrayOutputStream);
            hessianOutput.writeObject(t);
            hessianOutput.flush();
            hessianOutput.close();
            return byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    public static <T> byte[] hessian2SerialToByteArray(T t){
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();){
            Hessian2Output hessianOutput = new Hessian2Output(byteArrayOutputStream);
            hessianOutput.writeObject(t);
            hessianOutput.flush();
            hessianOutput.close();
            return byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T jdkDSerial(byte[] data){
        if(Objects.isNull(data)){
            return null;
        }
        Object result = null;
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        ){
            result = objectInputStream.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (T)result;
    }


    public static <T> T hessianDSerial(byte[] data){
        if(Objects.isNull(data)){
            return null;
        }
        Object result = null;
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ){
            HessianInput hessianInput = new HessianInput(byteArrayInputStream);
            result =  hessianInput.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (T)result;
    }

    public static <T> T hessian2DSerial(byte[] data){
        if(Objects.isNull(data)){
            return null;
        }
        Object result = null;
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ){
            Hessian2Input hessianInput = new Hessian2Input(byteArrayInputStream);
            result =  hessianInput.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (T)result;
    }


    public static <T> byte[] kryoSerial(T t){
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ){
            Kryo kryo = new Kryo();
            Output output = new Output(byteArrayOutputStream);
            kryo.writeObject(output,t);
            output.flush();
            output.close();
            return byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T kryoDSerial(byte[] data,Class<T> tClass){
        if(Objects.isNull(data)){
            return null;
        }
        Object result = null;
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ){
            Kryo kryo = new Kryo();
            Input input = new Input(byteArrayInputStream);
            result = kryo.readObject(input,tClass);
            input.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (T) result;
    }
}
